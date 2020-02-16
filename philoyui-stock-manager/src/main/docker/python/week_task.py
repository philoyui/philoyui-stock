import pandas as pd
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

from base import init_database_engine, delete_old_data
from strategy.kdj import process_kdj

engine = init_database_engine()
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)
Base = declarative_base()
Session = sessionmaker(bind=engine)
session = Session()

delete_old_data("周线KD金叉后第一天")
delete_old_data("周线KD金叉后第二天")
delete_old_data("周线KD金叉后第三天")
delete_old_data("周线KD金叉后多天")
delete_old_data("周线KD死叉后第一天")
delete_old_data("周线KD死叉后第二天")
delete_old_data("周线KD死叉后第三天")
delete_old_data("周线KD死叉后多天")


for stock_info in stock_list_df.values:

    sql = "select * from data_week_entity where symbol = '%s' order by day asc;" % stock_info[5]
    week_data_frame = pd.read_sql_query(sql, engine)
    if not week_data_frame.empty:
        # kdj
        process_kdj(stock_info, week_data_frame, "周线")
