import numpy

from base import mark_stock_as_tag
import talib
import pandas as pd
import numpy as np


'''
    八仙战法
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_eight_line(stock_data, data_frame, tag_prefix):
    closed = data_frame['close'].values
    avg20 = talib.MA(closed, timeperiod=20)
    avg22 = talib.MA(closed, timeperiod=22)
    avg24 = talib.MA(closed, timeperiod=24)
    avg30 = talib.MA(closed, timeperiod=30)
    avg50 = talib.MA(closed, timeperiod=50)
    avg60 = talib.MA(closed, timeperiod=60)
    avg65 = talib.MA(closed, timeperiod=65)
    avg72 = talib.MA(closed, timeperiod=72)
    if closed[-1] > avg20[-1] > avg22[-1] > avg24[-1] > avg30[-1] > avg50[-1] > avg60[-1] > avg65[-1] > avg72[-1] and\
            avg20[-2] < avg72[-2]:
        mark_stock_as_tag(stock_data, tag_prefix + "八线")
