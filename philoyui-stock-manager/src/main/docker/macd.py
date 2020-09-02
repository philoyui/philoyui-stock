import sys
import pandas as pd
import talib
from sqlalchemy import create_engine
import baostock as bs
import datetime

from base import build_mysql_connection

engine = create_engine(build_mysql_connection())
conn = engine.connect()

symbol = sys.argv[1][0:2] + '.' + sys.argv[1][2:8]
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

print(startDate)
rs = bs.query_history_k_data_plus(symbol,
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
macd_array, signal_array, hist_array = talib.MACD(close_array, fastperiod=12, slowperiod=26, signalperiod=9)


def mark_macd_value(macd_type_string, last_index):
    global sql
    sql = "INSERT INTO macd_data_entity (close_value, day, day_String, hist_value, interval_type, macd_type, " \
          "macd_value, signal_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + day_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + day_array[-1 - i] + "', " + str(hist_array[-1 - i]) + ", '" + interval_type + "', '" \
          + macd_type_string + "', " + str(macd_array[-1 - i]) + ", " + str(signal_array[-1 - i]) + ", '"\
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
