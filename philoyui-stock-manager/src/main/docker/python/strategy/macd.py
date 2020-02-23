import numpy

from base import mark_stock_as_tag
import talib

'''
    MACD策略
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_macd(stock_data, data_frame, tag_prefix):
    volume = data_frame['volume'].values
    open = data_frame['open'].values
    close = data_frame['close'].values

    macd, signal, hist = talib.MACD(close, fastperiod=12, slowperiod=26, signalperiod=9)
    if macd[-1] > signal[-1] and macd[-2] < signal[-2] and 0 < macd[-1] < 0.25:
        mark_stock_as_tag(stock_data, tag_prefix + "MACD0轴金叉1")
    if macd[-2] > signal[-2] and macd[-3] < signal[-3] and 0 < macd[-1] < 0.25:
        mark_stock_as_tag(stock_data, tag_prefix + "MACD0轴金叉1")