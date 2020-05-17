import sys

import pandas as pd
import talib
from sqlalchemy import create_engine

engine = create_engine('mysql+pymysql://root:123456@localhost:3306/stock_analysis')
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


boll_upper, boll_middle, boll_lower = talib.BBANDS(close_array, timeperiod=20, nbdevup=2, nbdevdn=2, matype=0)


def mark_boll_value(boll_type_string):
    global sql
    sql = "INSERT INTO boll_data_entity (close_value, day, day_String, boll_lower_value, interval_type, boll_type, " \
          "boll_middle_value, boll_upper_value, symbol) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', " + str(boll_lower[-1 - i]) + ", '" + interval_type + "', '" \
          + boll_type_string + "', " + str(boll_middle[-1 - i]) + ", " + str(boll_upper[-1 - i]) + ", '"\
          + symbol + "')"
    conn.execute(sql)


for i in range(len(close_array)-2):

    if boll_upper[-1-i] is None:
        break

    if boll_upper[-3-i] > boll_upper[-2-i] and boll_upper[-1-i] > boll_upper[-2-i]:
        mark_boll_value("EXPAND")
    if boll_upper[-3-i] < boll_upper[-2-i] and boll_upper[-1-i] < boll_upper[-2-i]:
        mark_boll_value("SHRINK")
    if close_array[-2-i] < boll_middle[-2-i] and close_array[-1-i] > boll_middle[-1-i]:
        mark_boll_value("BREAK_THROUGH_MIDDLE")
    if close_array[-2 - i] > boll_middle[-2 - i] and close_array[-1 - i] < boll_middle[-1 - i]:
        mark_boll_value("FALL_THROUGH_MIDDLE")
    if close_array[-2-i] < boll_upper[-2-i] and close_array[-1-i] > boll_upper[-1-i]:
        mark_boll_value("BREAK_THROUGH_UPPER")
    if close_array[-2 - i] > boll_upper[-2 - i] and close_array[-1 - i] < boll_upper[-1 - i]:
        mark_boll_value("FALL_THROUGH_UPPER")
    if close_array[-2 - i] < boll_lower[-2 - i] and close_array[-1 - i] > boll_lower[-1 - i]:
        mark_boll_value("BREAK_THROUGH_LOWER")
    if close_array[-2 - i] > boll_lower[-2 - i] and close_array[-1 - i] < boll_lower[-1 - i]:
        mark_boll_value("FALL_THROUGH_LOWER")
