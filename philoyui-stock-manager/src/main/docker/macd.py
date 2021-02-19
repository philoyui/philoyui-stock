import sys

import pandas as pd
import talib
from sqlalchemy import create_engine
import urllib3

from base import build_mysql_connection

engine = create_engine(build_mysql_connection())
conn = engine.connect()
http = urllib3.PoolManager()

symbol = sys.argv[1]
interval_type = sys.argv[2]
global table_name

if interval_type.__eq__("hour"):
    table_name = "hour_data_entity"
if interval_type.__eq__("min15"):
    table_name = "min15data_entity"
if interval_type.__eq__("min30"):
    table_name = "min30data_entity"

sql = "select * from " + table_name + " where symbol = '" + symbol + "' order by day asc;"
data_frame = pd.read_sql_query(sql, engine)
volume_array = data_frame['volume'].values
open_array = data_frame['open'].values
close_array = data_frame['close'].values
date_string_array = data_frame['date_string'].values
day_array = data_frame['day'].values

macd_array, signal_array, hist_array = talib.MACD(close_array, fastperiod=12, slowperiod=26, signalperiod=9)


def alert(msg):
    http.request('GET', "http://localhost:8081/api/notify/alert?symbol=" + symbol + "&msg=" + msg).read()
    pass


if macd_array[0] > signal_array[0] and macd_array[1] < signal_array[1]:
    golden_close_set = []
    golden_macd_set = []

    death_close_set = []
    death_macd_set = []

    for i in range(len(close_array) - 2):
        if macd_array[-1 - i] is not None:
            if macd_array[-1 - i] > signal_array[-1 - i] and macd_array[-2 - i] < signal_array[-2 - i]:
                golden_close_set.append(close_array[-1 - i])
                golden_macd_set.append(macd_array[-1 - i])
            if macd_array[-1 - i] < signal_array[-1 - i] and macd_array[-2 - i] > signal_array[-2 - i]:
                death_close_set.append(close_array[-1 - i])
                death_macd_set.append(macd_array[-1 - i])

    if len(golden_macd_set) > 1 and golden_macd_set[0] > golden_macd_set[1] and golden_close_set[0] < golden_close_set[1]:
        alert("发现" + interval_type + "底背离信息，当前信息close："
              + str(golden_close_set[0]) + " " + str(golden_close_set[1])
              + " macd: " + str(golden_macd_set[0]) + " " + str(golden_macd_set[1]))

    if len(death_macd_set) > 1 and death_macd_set[0] < death_macd_set[1] and death_close_set[0] > death_close_set[1]:
        alert("发现" + interval_type + "顶背离信息，当前信息close："
              + str(golden_close_set[0]) + " " + str(golden_close_set[1])
              + " macd: " + str(golden_macd_set[0]) + " " + str(golden_macd_set[1]))