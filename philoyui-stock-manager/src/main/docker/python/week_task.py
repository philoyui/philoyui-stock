import pandas as pd
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker

from base import init_database_engine, delete_old_data
from strategy.boll import process_boll
from strategy.kdj import process_kdj
from strategy.macd import process_macd
from strategy.wr import process_willr

engine = init_database_engine()
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)
Base = declarative_base()
Session = sessionmaker(bind=engine)
session = Session()

delete_old_data("周KD金叉1")
delete_old_data("周KD金叉2")

delete_old_data("周boll回踩")
delete_old_data("周MACD0轴金叉1")
delete_old_data("周MACD0轴金叉2")


for stock_info in stock_list_df.values:

    sql = "select * from data_week_entity where symbol = '%s' order by day asc;" % stock_info[5]
    week_data_frame = pd.read_sql_query(sql, engine)
    if not week_data_frame.empty:
        # kdj
        process_kdj(stock_info, week_data_frame, "周")
        # boll回踩
        process_boll(stock_info, week_data_frame, "周")

        process_macd(stock_info, week_data_frame, "周")