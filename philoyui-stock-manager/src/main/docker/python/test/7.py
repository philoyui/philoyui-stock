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
        closed = df['close'].values
        avg20 = talib.MA(closed, timeperiod=20)
        avg22 = talib.MA(closed, timeperiod=22)
        avg24 = talib.MA(closed, timeperiod=24)
        avg30 = talib.MA(closed, timeperiod=30)
        avg50 = talib.MA(closed, timeperiod=50)
        avg60 = talib.MA(closed, timeperiod=60)
        avg65 = talib.MA(closed, timeperiod=65)
        avg72 = talib.MA(closed, timeperiod=72)
        my_obj = pd.Series([avg20[-1], avg22[-1], avg24[-1], avg30[-1], avg50[-1], avg60[-1], avg65[-1], avg72[-1]])
        var_val = np.var(my_obj)
        if var_val < 0.0001 and closed[-1] > avg20[-1]:
            print(stock_info[5] + " " + str(closed[-1]) + " " + str(var_val))


for stock in stock_list_df.values:
    check_is_beyond(stock)
