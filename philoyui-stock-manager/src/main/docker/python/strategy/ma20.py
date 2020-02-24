import numpy

from base import mark_stock_as_tag
import talib

'''
    MA策略
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_ma20(stock_data, data_frame, tag_prefix):
    close = data_frame['close'].values

    avg20 = talib.MA(close, timeperiod=20)
    if close[-1] > avg20[-1] > avg20[-2] > close[-2]:
        mark_stock_as_tag(stock_data, tag_prefix + "20日均线金叉")
