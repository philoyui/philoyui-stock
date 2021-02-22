import datetime

import baostock as bs
import pandas as pd
import talib
from sqlalchemy import create_engine

from base import build_mysql_connection

bs.login()

now = datetime.datetime.now()
day_string = (now - datetime.timedelta(days=now.weekday())).strftime("%Y-%m-%d")
# day_string = "2021-01-05"

# 获取指定日期的指数、股票数据
stock_rs = bs.query_all_stock(day_string)
stock_df = stock_rs.get_data()
data_df = pd.DataFrame()

engine = create_engine(build_mysql_connection())
conn = engine.connect()


def truncate_tables():
    truncate_macd_sql = "delete from macd_data_entity where interval_type = '60min'"
    conn.execute(truncate_macd_sql)


def mark_macd_value(symbol, interval_type, macd_type_string, last_index):
    macd_sql = "INSERT INTO macd_data_entity (close_value, day, day_String, hist_value, interval_type, macd_type, " \
          "macd_value, signal_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(hist_array[-1 - i]) + ", '" + interval_type + "', '" \
          + macd_type_string + "', " + str(macd_array[-1 - i]) + ", " + str(signal_array[-1 - i]) + ", '"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(macd_sql)


truncate_tables()

for stock_code in stock_df["code"]:

    symbol_string = stock_code[0:2] + stock_code[3:9]

    startDay = (datetime.datetime.now() - datetime.timedelta(days=18)).strftime("%Y-%m-%d")

    interval_type_string = "Min60"

    hour_result = bs.query_history_k_data_plus(stock_code, "date,code,open,high,low,close,"
                                                          "volume,amount,adjustflag",
                                               start_date=startDay, end_date='',
                                               frequency="60", adjustflag="2")

    day_data_list = []
    while (hour_result.error_code == '0') & hour_result.next():
        day_data_list.append(hour_result.get_row_data())
    day_data_frame = pd.DataFrame(day_data_list, columns=hour_result.fields)

    if day_data_frame.size == 0:
        continue

    volume_array = day_data_frame['volume'].astype(float).values
    open_array = day_data_frame['open'].astype(float).values
    close_array = day_data_frame['close'].astype(float).values
    day_array = day_data_frame['date'].values
    high_array = day_data_frame['high'].astype(float).values
    low_array = day_data_frame['low'].astype(float).values

    macd_array, signal_array, hist_array = talib.MACD(close_array, fastperiod=12, slowperiod=26, signalperiod=9)

    for i in range(len(close_array)-2):

        if macd_array[-1-i] is not None:
            if macd_array[-1-i] > signal_array[-1-i] and macd_array[-2-i] < signal_array[-2-i]:
                mark_macd_value(symbol_string, interval_type_string, "GOLDEN_CROSS", -1-i)
            if macd_array[-1-i] < signal_array[-1-i] and macd_array[-2-i] > signal_array[-2-i]:
                mark_macd_value(symbol_string, interval_type_string, "DEATH_CROSS", -1-i)

bs.logout()
