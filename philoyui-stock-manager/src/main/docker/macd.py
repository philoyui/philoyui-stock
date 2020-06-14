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

macd_array, signal_array, hist_array = talib.MACD(close_array, fastperiod=12, slowperiod=26, signalperiod=9)


def mark_macd_value(macd_type_string, last_index):
    global sql
    sql = "INSERT INTO macd_data_entity (close_value, day, day_String, hist_value, interval_type, macd_type, " \
          "macd_value, signal_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', " + str(hist_array[-1 - i]) + ", '" + interval_type + "', '" \
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
