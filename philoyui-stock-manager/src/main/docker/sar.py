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

real = talib.SAR(high_array, low_array, acceleration=0.02, maximum=0.2)


def mark_sar_value(sar_type_string):
    global sql
    sql = "INSERT INTO sar_data_entity (close_value, day, day_String, interval_type, sar_type, " \
          "symbol) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', '" + interval_type + "', '" \
          + sar_type_string + "','"\
          + symbol + "')"
    conn.execute(sql)


for i in range(len(close_array)-2):

    if real[-1-i] is None:
        break

    if real[-1-i] < close_array[-1-i] and real[-2-i] > close_array[-2-i]:
        mark_sar_value("Buy")
    if real[-1-i] > close_array[-1-i] and real[-2-i] < close_array[-2-i]:
        mark_sar_value("Sell")
