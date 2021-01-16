#coding:utf-8
from __future__ import unicode_literals
import datetime
from sqlalchemy import create_engine
from base import build_mysql_connection
import baostock as bs
import pandas as pd
import urllib3
import logging

bs.login()

current_month = datetime.date.today().month
current_year = datetime.date.today().year
quarter = (current_month-1) // 3 + 1


if current_month < 5:
    assign_year = current_year - 1
    assign_quarter = 3
    last_year_1 = current_year - 2
    last_year_2 = current_year - 3
    last_year_3 = current_year - 4
if 5 <= current_month <= 8:
    assign_year = current_year
    assign_quarter = 1
    last_year_1 = current_year - 1
    last_year_2 = current_year - 2
    last_year_3 = current_year - 3
if 9 <= current_month <= 10:
    assign_year = current_year
    assign_quarter = 2
    last_year_1 = current_year - 1
    last_year_2 = current_year - 2
    last_year_3 = current_year - 3
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
http = urllib3.PoolManager()

stock_rs = bs.query_all_stock("2021-01-06")
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


def mark_symbol_detail(symbol, field, info ,value):
    http.request('GET', "http://localhost:8081/api/stock_detail/editField?symbol=" + symbol + "&field=" + field +
                 "&info=" + info + "&value=" + value).read()
    pass


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
    cash_flow_list = []
    operation_list = []

    rs_profit_1 = bs.query_profit_data(code=stock_code, year=last_year_1, quarter=4)
    rs_profit_2 = bs.query_profit_data(code=stock_code, year=last_year_2, quarter=4)
    rs_profit_3 = bs.query_profit_data(code=stock_code, year=last_year_3, quarter=4)
    rs_balance = bs.query_balance_data(code=stock_code, year=assign_year, quarter=assign_quarter)
    rs_cash_flow = bs.query_cash_flow_data(code=stock_code, year=assign_year, quarter=assign_quarter)
    rs_operation = bs.query_operation_data(code=stock_code, year=assign_year, quarter=assign_quarter)

    while (rs_profit_1.error_code == '0') & rs_profit_1.next():
        profit_list_1.append(rs_profit_1.get_row_data())
        profit_list_2.append(rs_profit_2.get_row_data())
        profit_list_3.append(rs_profit_3.get_row_data())
        balance_list.append(rs_balance.get_row_data())
        cash_flow_list.append(rs_cash_flow.get_row_data())
        operation_list.append(rs_operation.get_row_data())
    try:
        result_profit_1 = pd.DataFrame(profit_list_1, columns=rs_profit_1.fields)
        result_profit_2 = pd.DataFrame(profit_list_2, columns=rs_profit_2.fields)
        result_profit_3 = pd.DataFrame(profit_list_3, columns=rs_profit_3.fields)
        result_balance = pd.DataFrame(balance_list, columns=rs_balance.fields)
        result_cash_flow = pd.DataFrame(cash_flow_list, columns=rs_cash_flow.fields)
        result_operation = pd.DataFrame(operation_list, columns=rs_operation.fields)

        if result_profit_1.size > 0:
            try:
                if float(result_profit_1["roeAvg"][0]) >= 0.18 and float(result_profit_2["roeAvg"][0]) >= 0.18 and float(
                        result_profit_3["roeAvg"][0]) >= 0.18:
                    mark_tag_stock(symbol_string, "大白马")
                if float(result_profit_1["roeAvg"][0]) >= float(result_profit_2["roeAvg"][0]) >= \
                        float(result_profit_3["roeAvg"][0]):
                    mark_tag_stock(symbol_string, "三年业绩上升")

                if float(result_profit_1["gpMargin"][0]) < 0.1:
                    mark_symbol_detail(symbol_string, "gpMargin", "生意很难做", result_profit_1["gpMargin"][0])
                if 0.1 <= float(result_profit_1["gpMargin"][0]) < 0.2:
                    mark_symbol_detail(symbol_string, "gpMargin", "生意很艰辛", result_profit_1["gpMargin"][0])
                if 0.2 <= float(result_profit_1["gpMargin"][0]) < 0.3:
                    mark_symbol_detail(symbol_string, "gpMargin", "毛利还可以", result_profit_1["gpMargin"][0])
                if 0.3 <= float(result_profit_1["gpMargin"][0]) < 0.4:
                    mark_symbol_detail(symbol_string, "gpMargin", "毛利还不错", result_profit_1["gpMargin"][0])
                if 0.4 <= float(result_profit_1["gpMargin"][0]) < 0.55:
                    mark_symbol_detail(symbol_string, "gpMargin", "毛利很高", result_profit_1["gpMargin"][0])
                if 0.5 <= float(result_profit_1["gpMargin"][0]) < 0.7:
                    mark_symbol_detail(symbol_string, "gpMargin", "毛利超高", result_profit_1["gpMargin"][0])
                if float(result_profit_1["gpMargin"][0]) >= 0.7:
                    mark_symbol_detail(symbol_string, "gpMargin", "毛利堪比卖白粉", result_profit_1["gpMargin"][0])

                if float(result_profit_1["npMargin"][0]) < 0:
                    mark_symbol_detail(symbol_string, "npMargin", "这个生意赚不到钱", result_profit_1["npMargin"][0])
                if 0 <= float(result_profit_1["npMargin"][0]) < 10:
                    mark_symbol_detail(symbol_string, "npMargin", "税后利润一般", result_profit_1["npMargin"][0])
                if 10 <= float(result_profit_1["npMargin"][0]) < 20:
                    mark_symbol_detail(symbol_string, "npMargin", "税后利润不错", result_profit_1["npMargin"][0])
                if 20 <= float(result_profit_1["npMargin"][0]) < 30:
                    mark_symbol_detail(symbol_string, "npMargin", "税后利润优异", result_profit_1["npMargin"][0])
                if float(result_profit_1["npMargin"][0]) >= 30:
                    mark_symbol_detail(symbol_string, "npMargin", "即使税后也非常赚钱", result_profit_1["npMargin"][0])

                if float(result_profit_1["roeAvg"][0]) < 0:
                    mark_symbol_detail(symbol_string, "roeAvg", "股东在亏损", result_profit_1["roeAvg"][0])
                if 0 <= float(result_profit_1["roeAvg"][0]) < 0.1:
                    mark_symbol_detail(symbol_string, "roeAvg", "收益率不高.", result_profit_1["roeAvg"][0])
                if 0.1 <= float(result_profit_1["roeAvg"][0]) < 0.15:
                    mark_symbol_detail(symbol_string, "roeAvg", "还可以的收益", result_profit_1["roeAvg"][0])
                if 0.15 <= float(result_profit_1["roeAvg"][0]) < 0.2:
                    mark_symbol_detail(symbol_string, "roeAvg", "不错的回报率", result_profit_1["roeAvg"][0])
                if 0.2 <= float(result_profit_1["roeAvg"][0]) < 0.3:
                    mark_symbol_detail(symbol_string, "roeAvg", "能够打败巴菲特的回报率", result_profit_1["roeAvg"][0])
                if float(result_profit_1["roeAvg"][0]) > 0.3:
                    mark_symbol_detail(symbol_string, "roeAvg", "很牛逼的回报率", result_profit_1["roeAvg"][0])
            except ValueError as e:
                logging.exception(e)

        if result_cash_flow.size > 0:
            try:
                if float(result_cash_flow["CAToAsset"][0]) < 0.1:
                    mark_symbol_detail(symbol_string, "CAToAsset", "气很短", result_cash_flow["CAToAsset"][0])
                if 0.1 <= float(result_cash_flow["CAToAsset"][0]) < 0.15:
                    mark_symbol_detail(symbol_string, "CAToAsset", "气一般", result_cash_flow["CAToAsset"][0])
                if 0.15 <= float(result_cash_flow["CAToAsset"][0]) < 0.25:
                    mark_symbol_detail(symbol_string, "CAToAsset", "现金充足", result_cash_flow["CAToAsset"][0])
                if float(result_cash_flow["CAToAsset"][0]) >= 0.25:
                    mark_symbol_detail(symbol_string, "CAToAsset", "气很长", result_cash_flow["CAToAsset"][0])
            except ValueError as e:
                logging.exception(e)

        if result_operation.size > 0:
            try:
                if float(result_operation["NRTurnDays"][0]) < 15:
                    mark_symbol_detail(symbol_string, "NRTurnDays", "天天收现金", result_operation["NRTurnDays"][0])
                if 15 <= float(result_operation["NRTurnDays"][0]) < 80:
                    mark_symbol_detail(symbol_string, "NRTurnDays", "收款很快", result_operation["NRTurnDays"][0])
                if 80 <= float(result_operation["NRTurnDays"][0]) < 100:
                    mark_symbol_detail(symbol_string, "NRTurnDays", "收款速度一般", result_operation["NRTurnDays"][0])
                if 100 <= float(result_operation["NRTurnDays"][0]) < 150:
                    mark_symbol_detail(symbol_string, "NRTurnDays", "收款速度很慢", result_operation["NRTurnDays"][0])
                if float(result_operation["NRTurnDays"][0]) >= 150:
                    mark_symbol_detail(symbol_string, "NRTurnDays", "收款速度也太慢了吧!", result_operation["NRTurnDays"][0])

                if float(result_operation["AssetTurnRatio"][0]) < 1:
                    mark_symbol_detail(symbol_string, "AssetTurnRatio", "重资产,周转很慢，风险高，需关注现金", result_operation["AssetTurnRatio"][0])
                if 1 <= float(result_operation["AssetTurnRatio"][0]) < 1.5:
                    mark_symbol_detail(symbol_string, "AssetTurnRatio", "经营稳健,还不错.", result_operation["AssetTurnRatio"][0])
                if 1.5 <= float(result_operation["AssetTurnRatio"][0]) < 2:
                    mark_symbol_detail(symbol_string, "AssetTurnRatio", "经营效率优异", result_operation["AssetTurnRatio"][0])
                if float(result_operation["AssetTurnRatio"][0]) >= 2:
                    mark_symbol_detail(symbol_string, "AssetTurnRatio", "团队运营超一流!", result_operation["AssetTurnRatio"][0])

                if float(result_operation["INVTurnDays"][0]) < 10:
                    mark_symbol_detail(symbol_string, "INVTurnDays", "基本无存货,产品火爆.", result_operation["INVTurnDays"][0])
                if 10 <= float(result_operation["INVTurnDays"][0]) < 30:
                    mark_symbol_detail(symbol_string, "INVTurnDays", "货卖的很快,口碑好", result_operation["INVTurnDays"][0])
                if 30 <= float(result_operation["INVTurnDays"][0]) < 60:
                    mark_symbol_detail(symbol_string, "INVTurnDays", "货卖的不错", result_operation["INVTurnDays"][0])
                if 60 <= float(result_operation["INVTurnDays"][0]) < 100:
                    mark_symbol_detail(symbol_string, "INVTurnDays", "货卖的一般.", result_operation["INVTurnDays"][0])
                if 100 <= float(result_operation["INVTurnDays"][0]) < 150:
                    mark_symbol_detail(symbol_string, "INVTurnDays", "卖货很慢,属于原物料或低频消费品.", result_operation["INVTurnDays"][0])
                if float(result_operation["INVTurnDays"][0]) >= 150:
                    mark_symbol_detail(symbol_string, "INVTurnDays", "产品可能不好卖,特殊产业除外(酒类,地产等).", result_operation["INVTurnDays"][0])
            except ValueError as e:
                logging.exception(e)

        if result_balance.size > 0:
            try:
                if float(result_balance["liabilityToAsset"][0]) < 0.3:
                    mark_symbol_detail(symbol_string, "liabilityToAsset", "基本没什么杆杠，看来股东非常看好公司", result_balance["liabilityToAsset"][0])
                if 0.3 <= float(result_balance["liabilityToAsset"][0]) < 0.4:
                    mark_symbol_detail(symbol_string, "liabilityToAsset", "不用举债就能存活很好.", result_balance["liabilityToAsset"][0])
                if 0.4 <= float(result_balance["liabilityToAsset"][0]) < 0.6:
                    mark_symbol_detail(symbol_string, "liabilityToAsset", "杆杠稳健", result_balance["liabilityToAsset"][0])
                if 0.6 <= float(result_balance["liabilityToAsset"][0]) < 0.8:
                    mark_symbol_detail(symbol_string, "liabilityToAsset", "杆杠偏高.", result_balance["liabilityToAsset"][0])
                if float(result_balance["liabilityToAsset"][0]) >= 0.8:
                    mark_symbol_detail(symbol_string, "liabilityToAsset", "杆杠过大,风险偏高", result_balance["liabilityToAsset"][0])

                if float(result_balance["quickRatio"][0]) < 1.0:
                    mark_symbol_detail(symbol_string, "quickRatio", "如果发生债务纠纷,可能缺乏立即清偿能力.", result_balance["quickRatio"][0])
                if 1.0 <= float(result_balance["quickRatio"][0]) < 1.5:
                    mark_symbol_detail(symbol_string, "quickRatio", "即使发生债务纠纷,公司清偿问题不大.", result_balance["quickRatio"][0])
                if float(result_balance["quickRatio"][0]) > 1.5:
                    mark_symbol_detail(symbol_string, "quickRatio", "即使发生债务纠纷,公司也能立即清偿.", result_balance["quickRatio"][0])
            except ValueError as e:
                logging.exception(e)
    except AssertionError as e:
        logging.exception(e)



