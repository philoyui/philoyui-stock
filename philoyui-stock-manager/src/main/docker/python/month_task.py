import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

from strategy.kdj import process_kdj

engine = create_engine('mysql+pymysql://root:123456@114.67.84.99:32306/stock')
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)
Base = declarative_base()
Session = sessionmaker(bind=engine)
session = Session()

for stock_info in stock_list_df.values:

    sql = "select * from data_month_entity where symbol = '%s' order by day asc;" % stock_info[5]
    month_data_frame = pd.read_sql_query(sql, engine)
    if not month_data_frame.empty:
        # kdj
        process_kdj(stock_info, month_data_frame, "月线")
