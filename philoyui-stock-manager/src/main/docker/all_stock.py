import datetime

import baostock as bs
import pandas as pd
import talib
from sqlalchemy import create_engine

from base import build_mysql_connection

bs.login()

now = datetime.datetime.now()
day_string = (now - datetime.timedelta(days=now.weekday())).strftime("%Y-%m-%d")

# 获取指定日期的指数、股票数据
stock_rs = bs.query_all_stock(day_string)
stock_df = stock_rs.get_data()
data_df = pd.DataFrame()

engine = create_engine(build_mysql_connection())
conn = engine.connect()


def persist_stock_entity(symbol, stock_name):
    wr_sql = "INSERT INTO stock_entity (symbol, name) VALUES (\'" + symbol + "\',\'" + stock_name + "\')"
    conn.execute(wr_sql)


def truncate_tables():
    truncate_stock_sql = "truncate table stock_entity"
    conn.execute(truncate_stock_sql)


truncate_tables()

for index, row in stock_df.iterrows():
    symbol_string = row["code"][0:2] + row["code"][3:9]
    persist_stock_entity(symbol_string, row["code_name"])

bs.logout()
