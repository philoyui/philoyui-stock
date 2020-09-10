import math
import sys
import pandas as pd
import talib
from sqlalchemy import create_engine
import baostock as bs
import datetime

from base import build_mysql_connection

engine = create_engine(build_mysql_connection())
conn = engine.connect()

symbol = sys.argv[1]
interval_type = sys.argv[2]
global interval


if interval_type.__eq__("Day"):
    startDate = (datetime.datetime.now() - datetime.timedelta(days=120)).strftime("%Y-%m-%d")
    interval = "d"
if interval_type.__eq__("Week"):
    startDate = (datetime.datetime.now() - datetime.timedelta(days=840)).strftime("%Y-%m-%d")
    interval = "w"
if interval_type.__eq__("Month"):
    startDate = (datetime.datetime.now() - datetime.timedelta(days=3600)).strftime("%Y-%m-%d")
    interval = "m"


lg = bs.login()

rs = bs.query_history_k_data_plus(sys.argv[1][0:2] + '.' + sys.argv[1][2:8],
                                  "date,code,open,high,low,close,preclose,volume,amount,adjustflag,turn,tradestatus,"
                                  "pctChg,peTTM,pbMRQ,psTTM,pcfNcfTTM,isST",
                                  start_date=startDate, end_date='',
                                  frequency=interval, adjustflag="2")

data_list = []

while (rs.error_code == '0') & rs.next():
    data_list.append(rs.get_row_data())

result = pd.DataFrame(data_list, columns=rs.fields)

volume_array = result['volume'].astype(float).values
open_array = result['open'].astype(float).values
close_array = result['close'].astype(float).values
day_array = result['date'].values
high_array = result['high'].astype(float).values
low_array = result['low'].astype(float).values

low_list = result['low'].rolling(9, min_periods=9).min()
low_list.fillna(value=result['low'].expanding().min(), inplace=True)
high_list = result['high'].rolling(9, min_periods=9).max()
high_list.fillna(value=result['high'].expanding().max(), inplace=True)
rsv = (close_array - low_list) / (high_list - low_list) * 100
k = pd.DataFrame(rsv).ewm(com=2).mean()
d = k.ewm(com=2).mean()
j = 3 * k - 2 * d
length = close_array.size

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
wr20 = talib.WILLR(high_array, low_array, close_array, timeperiod=20)


def mark_macd_value(macd_type_string, last_index):
    global sql
    sql = "INSERT INTO macd_data_entity (close_value, day, day_String, hist_value, interval_type, macd_type, " \
          "macd_value, signal_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(hist_array[-1 - i]) + ", '" + interval_type + "', '" \
          + macd_type_string + "', " + str(macd_array[-1 - i]) + ", " + str(signal_array[-1 - i]) + ", '"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sql)


def mark_cci_value(cci_type_string, last_index):
    global sql
    sql = "INSERT INTO cci_data_entity (close_value, day, day_String, interval_type, cci_type, " \
          "cci_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', '" + interval_type + "', '" \
          + cci_type_string + "', " + str(cci_array[-1 - i]) + ", '" \
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sql)


def mark_kjd_value(kdj_type_string, last_index):
    global sql
    sql = "INSERT INTO kdj_data_entity (close_value, day, day_String, k_value, interval_type, kdj_type, " \
          "d_value, j_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(k.values[length - 1 - i][0]) + ", '" + interval_type + "', '" \
          + kdj_type_string + "', " + str(d.values[length - 1 - i][0]) + ", " + str(
        j.values[length - 1 - i][0]) + ", '" \
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sql)


def mark_ma_value(ma_type_string, last_index):
    if math.isnan(avg30[-1 - i]).__eq__(False):
        global sql
        sql = "INSERT INTO ma_data_entity (close_value, day, day_String, interval_type, ma_type, " \
              "symbol, ma5Value, ma10Value, ma20Value, ma30Value, last_index) VALUES (" + str(close_array[-1 - i]) + \
              ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
              + day_array[-1 - i] + "', '" + interval_type + "', '" \
              + ma_type_string + "','" \
              + symbol + "'," + str(avg5[-1 - i]) + "," + str(avg10[-1 - i]) + "," \
              + str(avg20[-1 - i]) + "," + str(avg30[-1 - i]) + "," + str(last_index) + ")"
        conn.execute(sql)


def mark_rsi_value(rsi_type_string, last_index):
    global sql
    sql = "INSERT INTO rsi_data_entity (close_value, day, day_String, interval_type, rsi_type, " \
          "symbol, rsi_value, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', '" + interval_type + "', '" \
          + rsi_type_string + "','"\
          + symbol + "'," + str(rsi[-1 - i]) + "," + str(last_index) + ")"
    conn.execute(sql)


def mark_sar_value(sar_type_string, last_index):
    global sql
    sql = "INSERT INTO sar_data_entity (close_value, day, day_String, interval_type, sar_type, " \
          "symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', '" + interval_type + "', '" \
          + sar_type_string + "','"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sql)


def mark_volume_value(ma_type_string, last_index):
    if math.isnan(volume_avg30[-1 - i]).__eq__(False):
        global sql
        sql = "INSERT INTO volume_data_entity (close_value, day, day_String, interval_type, volume_type, " \
              "symbol, ma5Value, ma10Value, ma20Value, ma30Value, last_index) VALUES (" + str(close_array[-1 - i]) + \
              ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
              + day_array[-1 - i] + "', '" + interval_type + "', '" \
              + ma_type_string + "','" \
              + symbol + "'," + str(volume_avg5[-1 - i]) + "," + str(volume_avg10[-1 - i]) + "," \
              + str(volume_avg20[-1 - i]) + "," + str(volume_avg30[-1 - i]) + "," + str(last_index) + ")"
        conn.execute(sql)


def mark_wr_value(wr_type_string, last_index):
    global sql
    sql = "INSERT INTO wr_data_entity (close_value, day, day_String, wr20value, interval_type, wr_type, " \
          " symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(wr20[-1 - i]) + ", '" + interval_type + "', '" \
          + wr_type_string + "', '" \
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sql)


for i in range(len(close_array)-2):

    if macd_array[-1-i] is None:
        break

    if macd_array[-1-i] > signal_array[-1-i] and macd_array[-2-i] < signal_array[-2-i]:
        mark_macd_value("GOLDEN_CROSS", -1-i)
    if macd_array[-1-i] < signal_array[-1-i] and macd_array[-2-i] > signal_array[-2-i]:
        mark_macd_value("DEATH_CROSS", -1-i)
    if macd_array[-1-i] > macd_array[-2-i] and macd_array[-2-i] < macd_array[-3-i]:
        mark_macd_value("BOTTOM_DIFF", -1-i)
    if macd_array[-1 - i] < macd_array[-2 - i] and macd_array[-2 - i] > macd_array[-3 - i]:
        mark_macd_value("TOP_DIFF", -1-i)

    if cci_array[-1-i] is None:
        break
    if cci_array[-1-i] > 100 and cci_array[-2-i] < 100:
        mark_cci_value("BREAK_100", -1-i)
    if cci_array[-1-i] < 100 and cci_array[-2-i] > 100:
        mark_cci_value("FALL_100", -1-i)
    if cci_array[-1-i] < -100 < cci_array[-2-i]:
        mark_cci_value("FALL_NEGATIVE_100", -1-i)
    if cci_array[-2-i] < -100 < cci_array[-1-i]:
        mark_cci_value("BREAK_NEGATIVE_100", -1-i)
    if cci_array[-2 - i] > cci_array[-3 - i] > 100 and cci_array[-1 - i] < cci_array[-2 - i] and cci_array[-2 - i] > 100 and \
            cci_array[-1 - i] > 100:
        mark_cci_value("TOP", -1-i)
    if cci_array[-2 - i] < cci_array[-3 - i] < -100 and cci_array[-1 - i] > cci_array[-2 - i] and cci_array[-2 - i] < -100 and\
            cci_array[-1 - i] < -100:
        mark_cci_value("BOTTOM", -1-i)

    if k.values[length - 1 - i] is None:
        break
    if d.values[length - 1 - i][0] < k.values[length - 1 - i][0] and d.values[length - 2 - i][0] > \
            k.values[length - 2 - i][0]:
        mark_kjd_value("GOLDEN_CROSS", -1 - i)
    if d.values[length - 1 - i][0] > k.values[length - 1 - i][0] and d.values[length - 2 - i][0] < \
            k.values[length - 2 - i][0]:
        mark_kjd_value("DEATH_CROSS", -1 - i)
    if k.values[length - 1 - i][0] <= 20 and d.values[length - 1 - i][0] < 28:
        mark_kjd_value("OVER_SELL", -1 - i)
    if k.values[length - 1 - i][0] >= 80 and d.values[length - 1 - i][0] > 72:
        mark_kjd_value("OVER_BUY", -1 - i)
    if k.values[length - 1 - i][0] > k.values[length - 2 - i][0] and k.values[length - 3 - i][0] > \
            k.values[length - 2 - i][0]:
        mark_kjd_value("BOTTOM_TURNING", -1 - i)
    if k.values[length - 1 - i][0] < k.values[length - 2 - i][0] and k.values[length - 3 - i][0] < \
            k.values[length - 2 - i][0]:
        mark_kjd_value("TOP_TURNING", -1 - i)

    if avg5[-1-i] is None:
        break
    if avg5[-1-i] > avg10[-1-i] and avg5[-2-i] < avg10[-2-i]:
        mark_ma_value("Cross_5_10_Golden", -1-i)
    if avg5[-1 - i] > avg20[-1 - i] and avg5[-2 - i] < avg20[-2 - i]:
        mark_ma_value("Cross_5_20_Golden", -1 - i)
    if avg5[-1 - i] < avg10[-1 - i] and avg5[-2 - i] > avg10[-2 - i]:
        mark_ma_value("Cross_5_10_Death", -1 - i)
    if avg5[-1 - i] < avg20[-1 - i] and avg5[-2 - i] > avg20[-2 - i]:
        mark_ma_value("Cross_5_20_Death", -1 - i)
    if avg5[-1-i] > avg5[-2-i] and avg10[-1-i] > avg10[-2-i] and avg30[-1-i] > avg30[-2-i] and avg30[-2-i] <\
            avg30[-3-i]:
        mark_ma_value("UpTrend", -1-i)
    if avg5[-1 - i] < avg5[-2 - i] and avg10[-1 - i] < avg10[-2 - i] and avg30[-1 - i] < avg30[-2 - i] \
            and avg30[-2 - i] > avg30[-3 - i]:
        mark_ma_value("DownTrend", -1 - i)

    if rsi[-1-i] is None:
        break
    if rsi[-1-i] > 70 and rsi[-2-i] < 70:
        mark_rsi_value("BREAK_70", -1-i)
    if rsi[-1-i] < 30 and rsi[-2-i] > 30:
        mark_rsi_value("FALL_30", -1-i)
    if rsi[-1 - i] < 70 and rsi[-2 - i] > 70:
        mark_rsi_value("FALL_70", -1-i)
    if rsi[-1 - i] > 30 and rsi[-2 - i] < 30:
        mark_rsi_value("BREAK_30", -1-i)
    if rsi[-2 - i] > rsi[-3 - i] > 70 and rsi[-1 - i] < rsi[-2 - i] and rsi[-2 - i] > 70 and \
            rsi[-1 - i] > 70:
        mark_rsi_value("TOP", -1-i)
    if rsi[-2 - i] < rsi[-3 - i] < 30 and rsi[-1 - i] > rsi[-2 - i] and rsi[-2 - i] < 30 and\
            rsi[-1 - i] < 30:
        mark_rsi_value("BOTTOM", -1-i)

    if real[-1 - i] is None:
        break
    if real[-1 - i] < close_array[-1 - i] and real[-2 - i] > close_array[-2 - i]:
        mark_sar_value("Buy", -1 - i)
    if real[-1 - i] > close_array[-1 - i] and real[-2 - i] < close_array[-2 - i]:
        mark_sar_value("Sell", -1 - i)

    if volume_avg5[-1 - i] is None:
        break

    if volume_avg5[-1 - i] > volume_avg10[-1 - i] and volume_avg5[-2 - i] < volume_avg10[-2 - i]:
        mark_volume_value("Cross_5_10_Golden", -1-i)
    if volume_avg5[-1 - i] > volume_avg20[-1 - i] and volume_avg5[-2 - i] < volume_avg20[-2 - i]:
        mark_volume_value("Cross_5_20_Golden", -1 - i)
    if volume_avg5[-1 - i] < volume_avg10[-1 - i] and volume_avg5[-2 - i] > volume_avg10[-2 - i]:
        mark_volume_value("Cross_5_10_Death", -1 - i)
    if volume_avg5[-1 - i] < volume_avg20[-1 - i] and volume_avg5[-2 - i] > volume_avg20[-2 - i]:
        mark_volume_value("Cross_5_20_Death", -1 - i)