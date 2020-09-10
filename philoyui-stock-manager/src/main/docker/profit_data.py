import baostock as bs
import pandas as pd

lg = bs.login()
profit_list = []
rs_profit = bs.query_profit_data(code="sh.601100", year=2017, quarter=2)
while (rs_profit.error_code == '0') & rs_profit.next():
    profit_list.append(rs_profit.get_row_data())
result_profit = pd.DataFrame(profit_list, columns=rs_profit.fields)
print(result_profit["roeAvg"].values)
bs.logout()