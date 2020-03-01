import talib

from base import mark_stock_as_tag

'''
    WR策略
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_sar(stock_data, data_frame, tag_prefix):
    close = data_frame['close'].values
    high = data_frame['high'].values
    low = data_frame['low'].values

    real = talib.SAR(high, low, acceleration=0.02, maximum=0.2)

    if real[-1] < close[-1] and real[-2] > close[-2]:
        mark_stock_as_tag(stock_data, tag_prefix + "SAR多头开始")
    if real[-1] > close[-1] and real[-2] < close[-2]:
        mark_stock_as_tag(stock_data, tag_prefix + "SAR空头开始")
