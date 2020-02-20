import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

from base import delete_old_data
from strategy.boll import process_boll
from strategy.kdj import process_kdj
from strategy.volume import process_volume

engine = create_engine('mysql+pymysql://root:123456@114.67.84.99:32306/stock')
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)
Base = declarative_base()
Session = sessionmaker(bind=engine)
session = Session()

delete_old_data("日KD金叉1")
delete_old_data("日KD金叉2")
delete_old_data("日KD金叉3")
delete_old_data("日KD金叉n")
delete_old_data("日KD死叉1")
delete_old_data("日KD死叉2")
delete_old_data("日KD死叉3")
delete_old_data("日KD死叉n")

delete_old_data("日boll回踩")

for stock_info in stock_list_df.values:

    sql = "select * from data_day_entity where symbol = '%s' order by day asc;" % stock_info[5]
    day_data_frame = pd.read_sql_query(sql, engine)
    if not day_data_frame.empty:
        # kdj
        process_kdj(stock_info, day_data_frame, "日")
        # boll回踩
        process_boll(stock_info, day_data_frame, "日")

        process_volume(stock_info, day_data_frame, "日")