import numpy

from base import mark_stock_as_tag
import talib
import pandas as pd
import numpy as np


'''
    CCI
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_cci(stock_data, data_frame, tag_prefix):
    closed = data_frame['close'].values
    high = data_frame['high'].values
    low = data_frame['low'].values
    result = talib.CCI(high, low, closed, 14)
    if result[-1] > 100 and result[-2] < 100:
        mark_stock_as_tag(stock_data, tag_prefix + "CCI多头")
    if result[-1] < -100 < result[-2]:
        mark_stock_as_tag(stock_data, tag_prefix + "CCI空头")