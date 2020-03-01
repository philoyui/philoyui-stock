import talib

from base import mark_stock_as_tag

'''
    WR策略
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_rsi(stock_data, data_frame, tag_prefix):
    close = data_frame['close'].values
    rsi = talib.RSI(close, timeperiod=14)
    if rsi[-1] < 30:
        mark_stock_as_tag(stock_data, tag_prefix + "RSI超卖")
    if rsi[-1] > 70:
        mark_stock_as_tag(stock_data, tag_prefix + "RSI超买")