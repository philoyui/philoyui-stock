import pandas as pd
import talib
from sqlalchemy import create_engine
import numpy as np

engine = create_engine('mysql+pymysql://root:123456@114.67.84.99:32306/stock')
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)

# boll中轨向上，趋势向上；当前价


def check_is_beyond(stock_info):
    sql = "select * from data_day_entity where symbol = '%s' order by day asc;" % stock_info[5]
    df = pd.read_sql_query(sql, engine)
    if not df.empty:
        closed = df['close'].values
        low = df['low'].values
        open = df['open'].values

        boll_upper, boll_middle, boll_lower = talib.BBANDS(closed, timeperiod=20, nbdevup=2, nbdevdn=2, matype=0)

        if boll_middle[-2] < closed[-2] < boll_upper[-2] and boll_middle[-1] < closed[-1] < boll_upper[-1] and\
                open[-2] > closed[-2] and closed[-1] > (open[-2] + closed[-2])/2 and (open[-2] + closed[-2])/2 <\
                (open[-3] + closed[-3])/2 and closed[-1] > open[-1]:
            print(stock[5])


for stock in stock_list_df.values:
    check_is_beyond(stock)
