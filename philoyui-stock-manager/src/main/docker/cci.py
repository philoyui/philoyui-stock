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

result = talib.CCI(high_array, low_array, close_array, 14)


def mark_cci_value(cci_type_string):
    global sql
    sql = "INSERT INTO cci_data_entity (close_value, day, day_String, interval_type, cci_type, " \
          "cci_value, symbol) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', '" + interval_type + "', '" \
          + cci_type_string + "', " + str(result[-1 - i]) + ", '"\
          + symbol + "')"
    conn.execute(sql)


for i in range(len(close_array)-2):

    if result[-1-i] is None:
        break

    if result[-1-i] > 100 and result[-2-i] < 100:
        mark_cci_value("BREAK_100")
    if result[-1-i] < 100 and result[-2-i] > 100:
        mark_cci_value("FALL_100")
    if result[-1-i] < -100 < result[-2-i]:
        mark_cci_value("FALL_NEGATIVE_100")
    if result[-2-i] < -100 < result[-1-i]:
        mark_cci_value("BREAK_NEGATIVE_100")
    if result[-2 - i] > result[-3 - i] > 100 and result[-1 - i] < result[-2 - i] and result[-2 - i] > 100 and \
            result[-1 - i] > 100:
        mark_cci_value("TOP")
    if result[-2 - i] < result[-3 - i] < -100 and result[-1 - i] > result[-2 - i] and result[-2 - i] < -100 and\
            result[-1 - i] < -100:
        mark_cci_value("BOTTOM")
