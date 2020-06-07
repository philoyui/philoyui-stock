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

rsi = talib.RSI(close_array, timeperiod=14)


def mark_rsi_value(rsi_type_string, last_index):
    global sql
    sql = "INSERT INTO rsi_data_entity (close_value, day, day_String, interval_type, rsi_type, " \
          "symbol, rsi_value, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', '" + interval_type + "', '" \
          + rsi_type_string + "','"\
          + symbol + "'," + str(rsi[-1 - i]) + "," + str(last_index) + ")"
    conn.execute(sql)


for i in range(len(close_array)-2):

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
