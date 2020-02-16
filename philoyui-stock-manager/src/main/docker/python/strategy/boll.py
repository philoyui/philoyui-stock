import talib

from base import mark_stock_as_tag

'''
    KDJ策略
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_boll(stock_data, data_frame, tag_prefix):
    closed = data_frame['close'].values
    low = data_frame['low'].values
    open = data_frame['open'].values

    boll_upper, boll_middle, boll_lower = talib.BBANDS(closed, timeperiod=20, nbdevup=2, nbdevdn=2, matype=0)

    if boll_middle[-2] < closed[-2] < boll_upper[-2] and boll_middle[-1] < closed[-1] < boll_upper[-1] and \
            open[-2] > closed[-2] and closed[-1] > (open[-2] + closed[-2]) / 2 and (open[-2] + closed[-2]) / 2 < \
            (open[-3] + closed[-3]) / 2 and closed[-1] > open[-1]:
        mark_stock_as_tag(stock_data, tag_prefix + "BOLL回踩")