import datetime
import math

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
    truncate_cci_sql = "truncate table cci_data_entity"
    conn.execute(truncate_cci_sql)
    truncate_ma_sql = "truncate table ma_data_entity"
    conn.execute(truncate_ma_sql)
    truncate_rsi_sql = "truncate table rsi_data_entity"
    conn.execute(truncate_rsi_sql)
    truncate_sar_sql = "truncate table sar_data_entity"
    conn.execute(truncate_sar_sql)
    truncate_volume_sql = "truncate table volume_data_entity"
    conn.execute(truncate_volume_sql)
    truncate_wr_sql = "truncate table wr_data_entity"
    conn.execute(truncate_wr_sql)
    truncate_wr_sql = "truncate table kdj_data_entity"
    conn.execute(truncate_wr_sql)
    truncate_tag_sql = "delete from stock_tag where interval_type = 0"
    conn.execute(truncate_tag_sql)


def mark_tag_stock(symbol, tag_name):
    tag_sql = "INSERT INTO stock_tag (created_time, symbol, tag_name, day_string, interval_type, last_index" \
          ") VALUES (str_to_date('" + day_string + "', '%%Y-%%m-%%d') , '" \
          + symbol + "', '" + tag_name + "', '" + day_string + "', 0, -1)"
    conn.execute(tag_sql)


def mark_macd_value(symbol, interval_type, macd_type_string, last_index):
    macd_sql = "INSERT INTO macd_data_entity (close_value, day, day_String, hist_value, interval_type, macd_type, " \
          "macd_value, signal_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(hist_array[-1 - i]) + ", '" + interval_type + "', '" \
          + macd_type_string + "', " + str(macd_array[-1 - i]) + ", " + str(signal_array[-1 - i]) + ", '"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(macd_sql)


def mark_cci_value(symbol, interval_type, cci_type_string, last_index):
    cci_sql = "INSERT INTO cci_data_entity (close_value, day, day_String, interval_type, cci_type, " \
          "cci_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', '" + interval_type + "', '" \
          + cci_type_string + "', " + str(cci_array[-1 - i]) + ", '" \
          + symbol + "', " + str(last_index) + ")"
    conn.execute(cci_sql)


def mark_ma_value(symbol, interval_type, ma_type_string, last_index):
    if math.isnan(avg30[-1 - i]).__eq__(False):
        ma_sql = "INSERT INTO ma_data_entity (close_value, day, day_String, interval_type, ma_type, " \
              "symbol, ma5Value, ma10Value, ma20Value, ma30Value, last_index) VALUES (" + str(close_array[-1 - i]) + \
              ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
              + day_array[-1 - i] + "', '" + interval_type + "', '" \
              + ma_type_string + "','" \
              + symbol + "'," + str(avg5[-1 - i]) + "," + str(avg10[-1 - i]) + "," \
              + str(avg20[-1 - i]) + "," + str(avg30[-1 - i]) + "," + str(last_index) + ")"
        conn.execute(ma_sql)


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


def mark_volume_value(symbol, interval_type, ma_type_string, last_index):
    if math.isnan(volume_avg30[-1 - i]).__eq__(False):
        volume_sql = "INSERT INTO volume_data_entity (close_value, day, day_String, interval_type, volume_type, " \
              "symbol, ma5Value, ma10Value, ma20Value, ma30Value, last_index) VALUES (" + str(close_array[-1 - i]) + \
              ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
              + day_array[-1 - i] + "', '" + interval_type + "', '" \
              + ma_type_string + "','" \
              + symbol + "'," + str(volume_avg5[-1 - i]) + "," + str(volume_avg10[-1 - i]) + "," \
              + str(volume_avg20[-1 - i]) + "," + str(volume_avg30[-1 - i]) + "," + str(last_index) + ")"
        conn.execute(volume_sql)


def mark_wr_value(symbol, interval_type, wr_type_string, last_index):
    wr_sql = "INSERT INTO wr_data_entity (close_value, day, day_String, wr20value, interval_type, wr_type, " \
          " symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
             + day_array[-1 - i] + "', " + str(wr14[-1 - i]) + ", '" + interval_type + "', '" \
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

    if symbol_string.startswith("sh00"):
        continue

    if symbol_string.startswith("sz399"):
        continue

    startDay = (datetime.datetime.now() - datetime.timedelta(days=120)).strftime("%Y-%m-%d")

    interval_type_string = "Day"

    day_result = bs.query_history_k_data_plus(stock_code, "date,code,open,high,low,close,"
                                                          "preclose,volume,amount,adjustflag,turn,tradestatus,"
                                                          "pctChg,peTTM,pbMRQ,psTTM,pcfNcfTTM,isST",
                                              start_date=startDay, end_date='',
                                              frequency="d", adjustflag="2")

    day_data_list = []
    while (day_result.error_code == '0') & day_result.next():
        day_data_list.append(day_result.get_row_data())
    day_data_frame = pd.DataFrame(day_data_list, columns=day_result.fields)

    try:
        volume_array = day_data_frame['volume'].astype(float).values
        open_array = day_data_frame['open'].astype(float).values
        close_array = day_data_frame['close'].astype(float).values
        day_array = day_data_frame['date'].values
        high_array = day_data_frame['high'].astype(float).values
        low_array = day_data_frame['low'].astype(float).values
        turn_array = day_data_frame['turn'].values
        peTTM_array = day_data_frame['peTTM'].values
        # 市销率
        psTTM_array = day_data_frame['psTTM'].values
        # 市净率
        pbMRQ_array = day_data_frame['pbMRQ'].values

        macd_array, signal_array, hist_array = talib.MACD(close_array, fastperiod=12, slowperiod=26, signalperiod=9)

        cci_array = talib.CCI(high_array, low_array, close_array, 14)

        avg5 = talib.MA(close_array, timeperiod=5)
        avg10 = talib.MA(close_array, timeperiod=10)
        avg20 = talib.MA(close_array, timeperiod=20)
        avg30 = talib.MA(close_array, timeperiod=30)

        rsi = talib.RSI(close_array, timeperiod=14)

        real = talib.SAR(high_array, low_array, acceleration=0.02, maximum=0.2)

        volume_avg5 = talib.MA(volume_array, timeperiod=5)
        volume_avg10 = talib.MA(volume_array, timeperiod=10)
        volume_avg20 = talib.MA(volume_array, timeperiod=20)
        volume_avg30 = talib.MA(volume_array, timeperiod=30)

        low_volume_percent = float(day_data_frame['volume'].head(n=1)) / float(day_data_frame['volume'].head(n=32).max())

        wr14 = talib.WILLR(high_array, low_array, close_array, timeperiod=14)

        low_list = day_data_frame['low'].astype(float).rolling(9, min_periods=9).min()
        low_list.fillna(value=day_data_frame['low'].astype(float).expanding().min(), inplace=True)
        high_list = day_data_frame['high'].astype(float).rolling(9, min_periods=9).max()
        high_list.fillna(value=day_data_frame['high'].astype(float).expanding().max(), inplace=True)
        rsv = (day_data_frame['close'].astype(float) - low_list) / (high_list - low_list) * 100
        k = pd.DataFrame(rsv).ewm(com=2).mean()
        d = k.ewm(com=2).mean()
        j = 3 * k - 2 * d

        length = day_data_frame['close'].size
    except ValueError:
        print(turn_array[-1 - i])
    except ZeroDivisionError:
        print(turn_array[-1 - i])

    for i in range(len(close_array)-5):

        if macd_array[-1-i] is not None:
            if macd_array[-1-i] > signal_array[-1-i] and macd_array[-2-i] < signal_array[-2-i] and macd_array[-3-i] \
                    < signal_array[-3-i]:
                mark_macd_value(symbol_string, interval_type_string, "GOLDEN_CROSS", -1-i)
            if macd_array[-1-i] < signal_array[-1-i] and macd_array[-2-i] > signal_array[-2-i] and macd_array[-3-i] \
                    > signal_array[-3-i]:
                mark_macd_value(symbol_string, interval_type_string, "DEATH_CROSS", -1-i)
        try:
            if cci_array[-1-i] is not None and cci_array[-2-i] is not None and cci_array[-3-i] is not None and cci_array[-4-i] is not None and cci_array[-5-i] is not None:
                if cci_array[-5-i] < cci_array[-4-i] < cci_array[-3 - i] and cci_array[-3 - i] > cci_array[-2 - i] >\
                        cci_array[-1 - i] > 100:
                    mark_cci_value(symbol_string, interval_type_string, "TOP", -1-i)
                if cci_array[-5-i] > cci_array[-4-i] > cci_array[-3 - i] and cci_array[-3 - i] < cci_array[-2 - i] <\
                        cci_array[-1 - i] < -100:
                    mark_cci_value(symbol_string, interval_type_string, "BOTTOM", -1-i)
        except IndexError:
            print(cci_array[-1-i])
        if avg5[-1-i] is not None:
            if avg5[-1-i] > avg10[-1-i] and avg5[-2-i] < avg10[-2-i]:
                mark_ma_value(symbol_string, interval_type_string, "Cross_5_10_Golden", -1-i)
            if avg5[-1 - i] > avg20[-1 - i] and avg5[-2 - i] < avg20[-2 - i]:
                mark_ma_value(symbol_string, interval_type_string, "Cross_5_20_Golden", -1 - i)
            if avg5[-1 - i] < avg10[-1 - i] and avg5[-2 - i] > avg10[-2 - i]:
                mark_ma_value(symbol_string, interval_type_string, "Cross_5_10_Death", -1 - i)
            if avg5[-1 - i] < avg20[-1 - i] and avg5[-2 - i] > avg20[-2 - i]:
                mark_ma_value(symbol_string, interval_type_string, "Cross_5_20_Death", -1 - i)

        if rsi[-1-i] is not None:
            if rsi[-2 - i] > rsi[-3 - i] > 50 and rsi[-1 - i] < rsi[-2 - i] and rsi[-2 - i] > 70 and \
                    rsi[-1 - i] > 50:
                mark_rsi_value(symbol_string, interval_type_string, "TOP", -1-i)
            if rsi[-2 - i] < rsi[-3 - i] < 50 and rsi[-1 - i] > rsi[-2 - i] and rsi[-2 - i] < 30 and\
                    rsi[-1 - i] < 50:
                mark_rsi_value(symbol_string, interval_type_string, "BOTTOM", -1-i)

        if volume_avg5[-1 - i] is not None:
            if volume_avg5[-1 - i] > volume_avg10[-1 - i] and volume_avg5[-2 - i] < volume_avg10[-2 - i]:
                mark_volume_value(symbol_string, interval_type_string, "Cross_5_10_Golden", -1-i)
            if volume_avg5[-1 - i] > volume_avg20[-1 - i] and volume_avg5[-2 - i] < volume_avg20[-2 - i]:
                mark_volume_value(symbol_string, interval_type_string, "Cross_5_20_Golden", -1 - i)
            if volume_avg5[-1 - i] < volume_avg10[-1 - i] and volume_avg5[-2 - i] > volume_avg10[-2 - i]:
                mark_volume_value(symbol_string, interval_type_string, "Cross_5_10_Death", -1 - i)
            if volume_avg5[-1 - i] < volume_avg20[-1 - i] and volume_avg5[-2 - i] > volume_avg20[-2 - i]:
                mark_volume_value(symbol_string, interval_type_string, "Cross_5_20_Death", -1 - i)

        if wr14[-1 - i] is not None:
            if wr14[-1 - i] > -85 > wr14[-2 - i]:
                mark_wr_value(symbol_string, interval_type_string, "Buy_Point_20", -1-i)
            if wr14[-1 - i] < -15 < wr14[-2 - i]:
                mark_wr_value(symbol_string, interval_type_string, "Sell_Point_20", -1-i)

        if k.values[length - 1 - i] is None:
            break

        if d.values[length - 1 - i][0] < k.values[length - 1 - i][0] and d.values[length - 2 - i][0] > \
                k.values[length - 2 - i][0]:
            mark_kjd_value(symbol_string, interval_type_string, "GOLDEN_CROSS", -1 - i)

        if d.values[length - 1 - i][0] > k.values[length - 1 - i][0] and d.values[length - 2 - i][0] < \
                k.values[length - 2 - i][0]:
            mark_kjd_value(symbol_string, interval_type_string, "DEATH_CROSS", -1 - i)

    try:
        if float(rsi[-1]) < 30.0:
            mark_tag_stock(symbol_string, "RSI超卖")

        if float(rsi[-1]) > 70.0:
            mark_tag_stock(symbol_string, "RSI超买")
    except ValueError:
        print(turn_array[-1 - i])

    try:
        if 0.0 < float(pbMRQ_array[-1]) <= 1.0:
            mark_tag_stock(symbol_string, "破净低估")
    except ValueError:
        print(pbMRQ_array[-1])

    try:
        if float(peTTM_array[-1]) <= 0.0:
            mark_tag_stock(symbol_string, "市盈率为负不盈利")
    except ValueError:
        print(peTTM_array[-1])

    try:
        if float(psTTM_array[-1]) <= 0.75:
            mark_tag_stock(symbol_string, "市销率有竞争力")
        if float(psTTM_array[-1]) > 10:
            mark_tag_stock(symbol_string, "市销率风险")
    except ValueError:
        print(psTTM_array[-1])

    print("parse " + symbol_string + " complete!!")

bs.logout()
