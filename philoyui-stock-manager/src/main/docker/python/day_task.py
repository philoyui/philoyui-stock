import pandas as pd
from sqlalchemy import create_engine
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from strategy.boll import process_boll
from strategy.cci import process_cci
from strategy.kdj import process_kdj
from strategy.ma20 import process_ma20
from strategy.ma_up import process_ma_up
from strategy.macd import process_macd
from strategy.rsi import process_rsi
from strategy.sar import process_sar
from strategy.volume import process_volume
from strategy.wr import process_willr

from base import delete_old_data

engine = create_engine('mysql+pymysql://root:123456@114.67.84.99:32306/stock')
stock_list_sql = "select * from financial_product_entity"
stock_list_df = pd.read_sql_query(stock_list_sql, engine)
Base = declarative_base()
Session = sessionmaker(bind=engine)
session = Session()

delete_old_data("日KD金叉1")
delete_old_data("日KD金叉2")

for stock_info in stock_list_df.values:

    sql = "select * from data_day_entity where symbol = '%s' order by day asc;" % stock_info[5]
    day_data_frame = pd.read_sql_query(sql, engine)
    if not day_data_frame.empty:
        # kdj
        process_kdj(stock_info, day_data_frame, "日")
