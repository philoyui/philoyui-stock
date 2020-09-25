import math

import datetime
from datetime import timedelta

import baostock as bs
import pandas as pd
import talib
from sqlalchemy import create_engine

from base import build_mysql_connection

bs.login()

now = datetime.datetime.now()
day_string = (now - datetime.timedelta(days=now.weekday())).strftime("%Y-%m-%d")

# 获取指定日期的指数、股票数据
stock_rs = bs.query_all_stock(day_string)
stock_df = stock_rs.get_data()
data_df = pd.DataFrame()

engine = create_engine(build_mysql_connection())
conn = engine.connect()


def truncate_tables():
    truncate_macd_sql = "truncate table macd_data_entity"
    conn.execute(truncate_macd_sql)
    truncate_rsi_sql = "truncate table rsi_data_entity"
    conn.execute(truncate_rsi_sql)
    truncate_sar_sql = "truncate table sar_data_entity"
    conn.execute(truncate_sar_sql)
    truncate_wr_sql = "truncate table wr_data_entity"
    conn.execute(truncate_wr_sql)
    truncate_wr_sql = "truncate table kdj_data_entity"
    conn.execute(truncate_wr_sql)


def mark_macd_value(symbol, interval_type, macd_type_string, last_index):
    macd_sql = "INSERT INTO macd_data_entity (close_value, day, day_String, hist_value, interval_type, macd_type, " \
          "macd_value, signal_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(hist_array[-1 - i]) + ", '" + interval_type + "', '" \
          + macd_type_string + "', " + str(macd_array[-1 - i]) + ", " + str(signal_array[-1 - i]) + ", '"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(macd_sql)


def mark_rsi_value(symbol, interval_type, rsi_type_string, last_index):
    rsi_sql = "INSERT INTO rsi_data_entity (close_value, day, day_String, interval_type, rsi_type, " \
          "symbol, rsi_value, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', '" + interval_type + "', '" \
          + rsi_type_string + "','"\
          + symbol + "'," + str(rsi[-1 - i]) + "," + str(last_index) + ")"
    conn.execute(rsi_sql)


def mark_sar_value(symbol, interval_type, sar_type_string, last_index):
    sar_sql = "INSERT INTO sar_data_entity (close_value, day, day_String, interval_type, sar_type, " \
          "symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', '" + interval_type + "', '" \
          + sar_type_string + "','"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sar_sql)


def mark_wr_value(symbol, interval_type, wr_type_string, last_index):
    wr_sql = "INSERT INTO wr_data_entity (close_value, day, day_String, wr20value, interval_type, wr_type, " \
          " symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
             + day_array[-1 - i] + "', " + str(wr20[-1 - i]) + ", '" + interval_type + "', '" \
             + wr_type_string + "', '" \
             + symbol + "', " + str(last_index) + ")"
    conn.execute(wr_sql)


def mark_kjd_value(symbol, interval_type, kdj_type_string, last_index):
    kdj_sql = "INSERT INTO kdj_data_entity (close_value, day, day_String, k_value, interval_type, kdj_type, " \
          "d_value, j_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(k.values[length-1-i][0]) + ", '" + interval_type + "', '" \
          + kdj_type_string + "', " + str(d.values[length-1-i][0]) + ", " + str(j.values[length-1-i][0]) + ", '"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(kdj_sql)


truncate_tables()

for stock_code in stock_df["code"]:

    symbol_string = stock_code[0:2] + stock_code[3:9]

    startDay = (datetime.datetime.now() - datetime.timedelta(days=504)).strftime("%Y-%m-%d")

    interval_type_string = "Week"

    week_result = bs.query_history_k_data_plus(stock_code, "date,code,open,high,low,close,volume,amount,adjustflag",
                                               start_date=startDay, end_date='',
                                               frequency="w", adjustflag="2")

    day_data_list = []
    while (week_result.error_code == '0') & week_result.next():
        day_data_list.append(week_result.get_row_data())
    day_data_frame = pd.DataFrame(day_data_list, columns=week_result.fields)

    open_array = day_data_frame['open'].astype(float).values
    close_array = day_data_frame['close'].astype(float).values
    day_array = day_data_frame['date'].values
    high_array = day_data_frame['high'].astype(float).values
    low_array = day_data_frame['low'].astype(float).values

    low_list = day_data_frame['low'].rolling(9, min_periods=9).min()
    low_list.fillna(value=day_data_frame['low'].expanding().min(), inplace=True)
    high_list = day_data_frame['high'].rolling(9, min_periods=9).max()
    high_list.fillna(value=day_data_frame['high'].expanding().max(), inplace=True)
    rsv = (close_array - low_list) / (high_list - low_list) * 100
    k = pd.DataFrame(rsv).ewm(com=2).mean()
    d = k.ewm(com=2).mean()
    j = 3 * k - 2 * d
    length = close_array.size

    macd_array, signal_array, hist_array = talib.MACD(close_array, fastperiod=12, slowperiod=26, signalperiod=9)

    rsi = talib.RSI(close_array, timeperiod=14)

    real = talib.SAR(high_array, low_array, acceleration=0.02, maximum=0.2)

    wr20 = talib.WILLR(high_array, low_array, close_array, timeperiod=20)

    for i in range(len(close_array)-2):

        if macd_array[-1-i] is not None:
            if macd_array[-1-i] > signal_array[-1-i] and macd_array[-2-i] < signal_array[-2-i]:
                mark_macd_value(symbol_string, interval_type_string, "GOLDEN_CROSS", -1-i)
            if macd_array[-1-i] < signal_array[-1-i] and macd_array[-2-i] > signal_array[-2-i]:
                mark_macd_value(symbol_string, interval_type_string, "DEATH_CROSS", -1-i)
            if macd_array[-1-i] > macd_array[-2-i] and macd_array[-2-i] < macd_array[-3-i]:
                mark_macd_value(symbol_string, interval_type_string, "BOTTOM_DIFF", -1-i)
            if macd_array[-1 - i] < macd_array[-2 - i] and macd_array[-2 - i] > macd_array[-3 - i]:
                mark_macd_value(symbol_string, interval_type_string, "TOP_DIFF", -1-i)

        if k.values[length - 1 - i] is not None:
            if d.values[length - 1 - i][0] < k.values[length - 1 - i][0] and d.values[length - 2 - i][0] > \
                    k.values[length - 2 - i][0]:
                mark_kjd_value(symbol_string, interval_type_string, "GOLDEN_CROSS", -1 - i)
            if d.values[length - 1 - i][0] > k.values[length - 1 - i][0] and d.values[length - 2 - i][0] < \
                    k.values[length - 2 - i][0]:
                mark_kjd_value(symbol_string, interval_type_string, "DEATH_CROSS", -1 - i)
            if k.values[length - 1 - i][0] > k.values[length - 2 - i][0] and k.values[length - 3 - i][0] > \
                    k.values[length - 2 - i][0]:
                mark_kjd_value(symbol_string, interval_type_string, "BOTTOM_TURNING", -1 - i)
            if k.values[length - 1 - i][0] < k.values[length - 2 - i][0] and k.values[length - 3 - i][0] < \
                    k.values[length - 2 - i][0]:
                mark_kjd_value(symbol_string, interval_type_string, "TOP_TURNING", -1 - i)

        if rsi[-1-i] is not None:
            if rsi[-1-i] > 70 and rsi[-2-i] < 70:
                mark_rsi_value(symbol_string, interval_type_string, "BREAK_70", -1-i)
            if rsi[-1-i] < 30 and rsi[-2-i] > 30:
                mark_rsi_value(symbol_string, interval_type_string, "FALL_30", -1-i)
            if rsi[-1 - i] < 70 and rsi[-2 - i] > 70:
                mark_rsi_value(symbol_string, interval_type_string, "FALL_70", -1-i)
            if rsi[-1 - i] > 30 and rsi[-2 - i] < 30:
                mark_rsi_value(symbol_string, interval_type_string, "BREAK_30", -1-i)
            if rsi[-2 - i] > rsi[-3 - i] > 70 and rsi[-1 - i] < rsi[-2 - i] and rsi[-2 - i] > 70 and \
                    rsi[-1 - i] > 70:
                mark_rsi_value(symbol_string, interval_type_string, "TOP", -1-i)
            if rsi[-2 - i] < rsi[-3 - i] < 30 and rsi[-1 - i] > rsi[-2 - i] and rsi[-2 - i] < 30 and\
                    rsi[-1 - i] < 30:
                mark_rsi_value(symbol_string, interval_type_string, "BOTTOM", -1-i)

        if real[-1 - i] is not None:
            if real[-1 - i] < close_array[-1 - i] and real[-2 - i] > close_array[-2 - i]:
                mark_sar_value(symbol_string, interval_type_string, "Buy", -1 - i)
            if real[-1 - i] > close_array[-1 - i] and real[-2 - i] < close_array[-2 - i]:
                mark_sar_value(symbol_string, interval_type_string, "Sell", -1 - i)

        if wr20[-1 - i] is not None:
            if wr20[-1 - i] > -85 > wr20[-2 - i]:
                mark_wr_value(symbol_string, interval_type_string, "Buy_Point_20", -1 - i)
            if wr20[-1 - i] < -15 < wr20[-2 - i]:
                mark_wr_value(symbol_string, interval_type_string, "Sell_Point_20", -1 - i)

    print("parse " + symbol_string + " complete!!")

bs.logout()
