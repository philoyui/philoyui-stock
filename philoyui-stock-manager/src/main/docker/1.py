import datetime
import math

import baostock as bs
import pandas as pd
import talib
from sqlalchemy import create_engine

from base import build_mysql_connection

bs.login()

now = datetime.datetime.now()
day_string = (now - datetime.timedelta(days=now.weekday())).strftime("%Y-%m-%d")

# 获取指定日期的指数、股票数据
stock_rs = bs.query_all_stock(day_string)
stock_df = stock_rs.get_data()
data_df = pd.DataFrame()

engine = create_engine(build_mysql_connection())
conn = engine.connect()


for stock_code in stock_df["code"]:

    symbol_string = stock_code[0:2] + stock_code[3:9]

    startDay = (datetime.datetime.now() - datetime.timedelta(days=120)).strftime("%Y-%m-%d")

    interval_type_string = "Day"

    day_result = bs.query_history_k_data_plus(stock_code, "date,code,open,high,low,close,"
                                                          "preclose,volume,amount,adjustflag,turn,tradestatus,"
                                                          "pctChg,peTTM,pbMRQ,psTTM,pcfNcfTTM,isST",
                                              start_date=startDay, end_date='',
                                              frequency="d", adjustflag="2")

    day_data_list = []
    while (day_result.error_code == '0') & day_result.next():
        day_data_list.append(day_result.get_row_data())
    day_data_frame = pd.DataFrame(day_data_list, columns=day_result.fields)

    try:
        # volume_array = day_data_frame['volume'].astype(float).values
        print(float(day_data_frame['volume'].head(n=1))/float(day_data_frame['volume'].head(n=32).max()))
    except ValueError:
        print("ERROR")

bs.logout()
