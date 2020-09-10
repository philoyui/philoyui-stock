import baostock as bs
import pandas as pd
import talib

lg = bs.login()

rs = bs.query_history_k_data_plus("sh.601100",
                                  "date,code,open,high,low,close,preclose,volume,amount,adjustflag,turn,tradestatus,"
                                  "pctChg,peTTM,pbMRQ,psTTM,pcfNcfTTM,isST",
                                  start_date='2019-01-01', end_date='',
                                  frequency="d", adjustflag="2")

data_list = []
while (rs.error_code == '0') & rs.next():
    data_list.append(rs.get_row_data())
result = pd.DataFrame(data_list, columns=rs.fields)

macd_array, signal_array, hist_array = talib.MACD(result['close'].astype(float).values, fastperiod=12, slowperiod=26, signalperiod=9)

volume_array = result['volume'].values
open_array = result['open'].values
close_array = result['close'].values
date_string_array = result['date'].values

for i in range(len(close_array)-2):

    if macd_array[-1-i] is None:
        break

    if macd_array[-1-i] > signal_array[-1-i] and macd_array[-2-i] < signal_array[-2-i]:
        print("GOLDEN_CROSS" + str(date_string_array[-1-i]))
    if macd_array[-1-i] < signal_array[-1-i] and macd_array[-2-i] > signal_array[-2-i]:
        print("DEATH_CROSS" + str(date_string_array[-1-i]))
    if macd_array[-1-i] > macd_array[-2-i] and macd_array[-2-i] < macd_array[-3-i]:
        print("BOTTOM_DIFF" + str(date_string_array[-1-i]))
    if macd_array[-1 - i] < macd_array[-2 - i] and macd_array[-2 - i] > macd_array[-3 - i]:
        print("TOP_DIFF" + str(date_string_array[-1-i]))

bs.logout()
