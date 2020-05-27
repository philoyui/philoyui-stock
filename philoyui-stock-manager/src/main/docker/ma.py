import sys

import pandas as pd
import talib
from sqlalchemy import create_engine

from base import build_mysql_connection

engine = create_engine(build_mysql_connection())
conn = engine.connect()

symbol = sys.argv[1]
interval_type = sys.argv[2]
global table_name

if interval_type.__eq__("Day"):
    table_name = "data_day_entity"
if interval_type.__eq__("Week"):
    table_name = "data_week_entity"
if interval_type.__eq__("Month"):
    table_name = "data_month_entity"

sql = "select * from " + table_name + " where symbol = '" + symbol + "' order by day asc;"
data_frame = pd.read_sql_query(sql, engine)
volume_array = data_frame['volume'].values
open_array = data_frame['open'].values
close_array = data_frame['close'].values
date_string_array = data_frame['date_string'].values
day_array = data_frame['day'].values
high_array = data_frame['high'].values
low_array = data_frame['low'].values

avg5 = talib.MA(close_array, timeperiod=5)
avg10 = talib.MA(close_array, timeperiod=10)
avg20 = talib.MA(close_array, timeperiod=20)
avg30 = talib.MA(close_array, timeperiod=30)


def mark_ma_value(ma_type_string):
    global sql
    sql = "INSERT INTO ma_data_entity (close_value, day, day_String, interval_type, ma_type, " \
          "symbol, ma5Value, ma10Value, ma20Value, ma30Value) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', '" + interval_type + "', '" \
          + ma_type_string + "','"\
          + symbol + "'," + str(avg5[-1 - i]) + "," + str(avg10[-1 - i]) + ","\
          + str(avg20[-1 - i]) + "," + str(avg30[-1 - i]) + ")"
    conn.execute(sql)


for i in range(len(close_array)-2):

    if avg5[-1-i] is None:
        break

    if avg5[-1-i] > avg5[-2-i] and avg10[-1-i] > avg10[-2-i] and avg30[-1-i] > avg30[-2-i] and avg30[-2-i] <\
            avg30[-3-i]:
        mark_ma_value("UpTrend")
    if avg5[-1 - i] < avg5[-2 - i] and avg10[-1 - i] < avg10[-2 - i] and avg30[-1 - i] < avg30[-2 - i] \
            and avg30[-2 - i] > avg30[-3 - i]:
        mark_ma_value("DownTrend")