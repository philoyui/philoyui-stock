import pandas as pd
from sqlalchemy import create_engine

engine = create_engine('mysql+pymysql://root:123456@114.67.84.99:32306/stock')
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)


def is_kd_week_gold_cross(stock, df):
    close = df['close'].values

    low_list = df['low'].rolling(9, min_periods=9).min()
    low_list.fillna(value=df['low'].expanding().min(), inplace=True)
    high_list = df['high'].rolling(9, min_periods=9).max()
    high_list.fillna(value=df['high'].expanding().max(), inplace=True)
    rsv = (df['close'] - low_list) / (high_list - low_list) * 100
    k = pd.DataFrame(rsv).ewm(com=2).mean()
    d = k.ewm(com=2).mean()
    j = 3 * k - 2 * d

    length = df['close'].size
    if k.values[length-1][0] > d.values[length-1][0] and k.values[length-2][0] < d.values[length-2][0]:
        return True
    else:
        return False


stock_list = []
for stock_info in stock_list_df.values:
    sql = "select * from data_week_entity where symbol = '%s' order by day asc;" % stock_info[5]
    df = pd.read_sql_query(sql, engine)
    if not df.empty:
        if is_kd_week_gold_cross(stock_info, df):
            stock_list.append(stock_info[5])
            print(stock_list)
