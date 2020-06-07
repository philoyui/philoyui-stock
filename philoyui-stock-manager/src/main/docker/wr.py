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

wr20 = talib.WILLR(high_array, low_array, close_array, timeperiod=20)


def mark_wr_value(wr_type_string, last_index):
    global sql
    sql = "INSERT INTO wr_data_entity (close_value, day, day_String, wr20value, interval_type, wr_type, " \
          " symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', " + str(wr20[-1-i]) + ", '" + interval_type + "', '" \
          + wr_type_string + "', '"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sql)


for i in range(len(close_array)-2):

    if wr20[-1-i] is None:
        break

    if wr20[-1 - i] > -85 > wr20[-2 - i]:
        mark_wr_value("Buy_Point_20", -1-i)
    if wr20[-1 - i] < -15 < wr20[-2 - i]:
        mark_wr_value("Sell_Point_20", -1-i)
