import datetime

from sqlalchemy import create_engine
from base import build_mysql_connection
import baostock as bs
import pandas as pd

bs.login()

current_month = datetime.date.today().month
current_year = datetime.date.today().year
quarter = (current_month-1) // 3 + 1


# 去年三季度
if current_month < 5:
    assign_year = current_year - 1
    assign_quarter = 3
    last_year_1 = current_year - 2
    last_year_2 = current_year - 3
    last_year_3 = current_year - 4
# 可查询一季报和去年年报
if 5 <= current_month <= 8:
    assign_year = current_year
    assign_quarter = 1
    last_year_1 = current_year - 1
    last_year_2 = current_year - 2
    last_year_3 = current_year - 3
# 可查询二季度
if 9 <= current_month <= 10:
    assign_year = current_year
    assign_quarter = 2
    last_year_1 = current_year - 1
    last_year_2 = current_year - 2
    last_year_3 = current_year - 3
# 可查三季度
if 11 <= current_month <= 12:
    assign_year = current_year
    assign_quarter = 3
    last_year_1 = current_year - 1
    last_year_2 = current_year - 2
    last_year_3 = current_year - 3

now = datetime.datetime.now()
day_string = (now - datetime.timedelta(days=now.weekday())).strftime("%Y-%m-%d")

engine = create_engine(build_mysql_connection())
conn = engine.connect()

# 获取指定日期的指数、股票数据
stock_rs = bs.query_all_stock("2021-01-01")
stock_df = stock_rs.get_data()
data_df = pd.DataFrame()


def truncate_tables():
    truncate_tag_sql = "delete from stock_tag where interval_type = 2"
    conn.execute(truncate_tag_sql)


def mark_tag_stock(symbol, tag_name):
    tag_sql = "INSERT INTO stock_tag (created_time, symbol, tag_name, day_string, interval_type, last_index" \
          ") VALUES (str_to_date('" + day_string + "', '%%Y-%%m-%%d') , '" \
          + symbol + "', '" + tag_name + "', '" + day_string + "', 2, -1)"
    conn.execute(tag_sql)


truncate_tables()

for stock_code in stock_df["code"]:

    symbol_string = stock_code[0:2] + stock_code[3:9]

    if symbol_string.startswith("sh00"):
        continue

    if symbol_string.startswith("sz399"):
        continue

    profit_list_1 = []
    profit_list_2 = []
    profit_list_3 = []
    balance_list = []

    rs_profit_1 = bs.query_profit_data(code=stock_code, year=last_year_1, quarter=4)
    rs_profit_2 = bs.query_profit_data(code=stock_code, year=last_year_2, quarter=4)
    rs_profit_3 = bs.query_profit_data(code=stock_code, year=last_year_3, quarter=4)
    rs_balance = bs.query_balance_data(code=stock_code, year=assign_year, quarter=assign_quarter)

    while (rs_profit_1.error_code == '0') & rs_profit_1.next():
        profit_list_1.append(rs_profit_1.get_row_data())
        profit_list_2.append(rs_profit_2.get_row_data())
        profit_list_3.append(rs_profit_3.get_row_data())
        balance_list.append(rs_balance.get_row_data())
    try:
        result_profit_1 = pd.DataFrame(profit_list_1, columns=rs_profit_1.fields)
        result_profit_2 = pd.DataFrame(profit_list_2, columns=rs_profit_2.fields)
        result_profit_3 = pd.DataFrame(profit_list_3, columns=rs_profit_3.fields)
        result_balance = pd.DataFrame(balance_list, columns=rs_balance.fields)

        if result_profit_1.size > 0:
            try:
                if float(result_profit_1["roeAvg"][0]) >= 0.18 and float(result_profit_2["roeAvg"][0]) >= 0.18 and float(
                        result_profit_3["roeAvg"][0]) >= 0.18:
                    mark_tag_stock(symbol_string, "大白马")
                if float(result_profit_1["roeAvg"][0]) >= float(result_profit_2["roeAvg"][0]) >= \
                        float(result_profit_3["roeAvg"][0]):
                    mark_tag_stock(symbol_string, "三年业绩上升")
            except ValueError:
                print(stock_code)

        if result_balance.size > 0:
            try:
                if float(result_balance["quickRatio"][0]) < 1.0:
                    mark_tag_stock(symbol_string, "债务风险")
            except ValueError:
                print(stock_code)
    except AssertionError:
        print(stock_code)


