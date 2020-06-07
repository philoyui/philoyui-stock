import sys

import pandas as pd
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

low_list = data_frame['low'].rolling(9, min_periods=9).min()
low_list.fillna(value=data_frame['low'].expanding().min(), inplace=True)
high_list = data_frame['high'].rolling(9, min_periods=9).max()
high_list.fillna(value=data_frame['high'].expanding().max(), inplace=True)
rsv = (data_frame['close'] - low_list) / (high_list - low_list) * 100
k = pd.DataFrame(rsv).ewm(com=2).mean()
d = k.ewm(com=2).mean()
j = 3 * k - 2 * d

length = data_frame['close'].size


def mark_kjd_value(kdj_type_string, last_index):
    global sql
    sql = "INSERT INTO kdj_data_entity (close_value, day, day_String, k_value, interval_type, kdj_type, " \
          "d_value, j_value, symbol, last_index) VALUES (" + str(close_array[-1 - i]) + \
          ", str_to_date('" + date_string_array[-1 - i] + "', '%%Y-%%m-%%d %%H:%%i:%%s') , '" \
          + date_string_array[-1 - i] + "', " + str(k.values[length-1-i][0]) + ", '" + interval_type + "', '" \
          + kdj_type_string + "', " + str(d.values[length-1-i][0]) + ", " + str(j.values[length-1-i][0]) + ", '"\
          + symbol + "', " + str(last_index) + ")"
    conn.execute(sql)


for i in range(len(close_array)-2):

    if k.values[length-1-i] is None:
        break

    if d.values[length-1-i][0] < k.values[length-1-i][0] and d.values[length-2-i][0] > k.values[length-2-i][0]:
        mark_kjd_value("GOLDEN_CROSS", -1-i)

    if d.values[length-1-i][0] > k.values[length-1-i][0] and d.values[length-2-i][0] < k.values[length-2-i][0]:
        mark_kjd_value("DEATH_CROSS", -1-i)

    if k.values[length - 1 - i][0] <= 20 and d.values[length - 1 - i][0] < 28:
        mark_kjd_value("OVER_SELL", -1-i)

    if k.values[length - 1 - i][0] >= 80 and d.values[length - 1 - i][0] > 72:
        mark_kjd_value("OVER_BUY", -1-i)

    if k.values[length-1-i][0] > k.values[length-2-i][0] and k.values[length-3-i][0] > k.values[length-2-i][0]:
        mark_kjd_value("BOTTOM_TURNING", -1-i)

    if k.values[length-1-i][0] < k.values[length-2-i][0] and k.values[length-3-i][0] < k.values[length-2-i][0]:
        mark_kjd_value("TOP_TURNING", -1-i)