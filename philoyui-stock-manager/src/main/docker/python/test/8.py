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

        last_2_day = (avg20[-2] > avg22[-2]) and (avg22[-2] > avg24[-2]) and (avg24[-2] > avg30[-2]) and (avg30[-2] > avg50[-2]) and (avg50[-2] > avg60[-2]) and (avg60[-2] > avg65[-2]) and (avg65[-2] > avg72[-2])
        my_obj = pd.Series([avg20[-1], avg22[-1], avg24[-1], avg30[-1], avg50[-1], avg60[-1], avg65[-1], avg72[-1]])
        varVal = np.var(my_obj)

        increase20 = avg20[-1] > avg20[-2]
        increase22 = avg22[-1] > avg22[-2]
        increase24 = avg24[-1] > avg24[-2]
        increase30 = avg30[-1] > avg30[-2]
        increase50 = avg50[-1] > avg50[-2]
        increase60 = avg60[-1] > avg60[-2]
        increase65 = avg65[-1] > avg65[-2]
        increase72 = avg72[-1] > avg72[-2]

        all_increase = increase20 and increase22 and increase24 and increase30 and increase50 and increase60 and increase65 and increase72
        last_day = (avg20[-1] > avg22[-1]) and (avg22[-1] > avg24[-1]) and (avg24[-1] > avg30[-1]) and (avg30[-1] > avg50[-1]) and (avg50[-1] > avg60[-1]) and (avg60[-1] > avg65[-1]) and (avg65[-1] > avg72[-1])
        # cross = avg20[-2] < avg72[-2] and avg20[-1] > avg72[-1]

        if last_day and varVal < 0.001 and closed[-1] > avg20[-1]:
            print(stock_info[4] + " " + stock_info[5] + " 符合八仙战法")


for stock in stock_list_df.values:
    check_is_beyond(stock)
