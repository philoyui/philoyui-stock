import pandas as pd
import talib
from sqlalchemy import create_engine
import numpy as np

engine = create_engine('mysql+pymysql://root:123456@114.67.84.99:32306/stock')
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)


def check_is_beyond(stock_info):
    sql = "select * from data_day_entity where symbol = '%s' order by day asc;" % stock_info[5]
    df = pd.read_sql_query(sql, engine)
    if not df.empty:
        volume = df['volume'].values
        open = df['open'].values
        close = df['close'].values

        if volume[-1] > (volume[-2]*3) and -0.06 < ((open[-1]-close[-1])/open[-1]) < 0.06:
            print(stock[5])


for stock in stock_list_df.values:
    check_is_beyond(stock)
