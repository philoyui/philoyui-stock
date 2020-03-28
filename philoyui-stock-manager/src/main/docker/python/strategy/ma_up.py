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


def process_ma_up(stock_data, data_frame, tag_prefix):
    closed = data_frame['close'].values
    avg10 = talib.MA(closed, timeperiod=10)
    avg20 = talib.MA(closed, timeperiod=20)
    avg30 = talib.MA(closed, timeperiod=30)
    if avg10[-1] > avg10[-2] and avg20[-1] > avg20[-2] and avg30[-1] > avg30[-2]:
        mark_stock_as_tag(stock_data, tag_prefix + "均线多头排列")
