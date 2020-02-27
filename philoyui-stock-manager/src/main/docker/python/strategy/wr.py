import numpy

from base import mark_stock_as_tag
import talib

'''
    WR策略
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_willr(stock_data, data_frame, tag_prefix):
    high = data_frame['high'].values
    low = data_frame['low'].values
    close = data_frame['close'].values

    wr1 = talib.WILLR(high, low, close, timeperiod=10)
    wr2 = talib.WILLR(high, low, close, timeperiod=20)

    if wr1[-1] < -80 and wr2[-1] < -80 and close[-1] > close[-2]:
        mark_stock_as_tag(stock_data, "威廉超买")
    if wr1[-1] > -20 and wr2[-1] > -20 and close[-1] < close[-2]:
        mark_stock_as_tag(stock_data, "威廉超卖")
