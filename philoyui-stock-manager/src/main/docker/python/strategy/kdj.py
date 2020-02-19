import pandas as pd

from base import mark_stock_as_tag, delete_old_data

'''
    KDJ策略
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_kdj(stock_data, data_frame, tag_prefix):
    close = data_frame['close'].values
    low_list = data_frame['low'].rolling(9, min_periods=9).min()
    low_list.fillna(value=data_frame['low'].expanding().min(), inplace=True)
    high_list = data_frame['high'].rolling(9, min_periods=9).max()
    high_list.fillna(value=data_frame['high'].expanding().max(), inplace=True)
    rsv = (data_frame['close'] - low_list) / (high_list - low_list) * 100
    k = pd.DataFrame(rsv).ewm(com=2).mean()
    d = k.ewm(com=2).mean()
    j = 3 * k - 2 * d
    length = data_frame['close'].size
    if k.values[length - 1][0] > d.values[length - 1][0] and k.values[length - 2][0] < d.values[length - 2][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD金叉1")
    if k.values[length - 1][0] > d.values[length - 1][0] and k.values[length - 2][0] > d.values[length - 2][0] and \
            k.values[length - 3][0] < d.values[length - 3][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD金叉2")
    if k.values[length - 1][0] > d.values[length - 1][0] and k.values[length - 2][0] > d.values[length - 2][0] and \
            k.values[length - 3][0] > d.values[length - 3][0] and k.values[length - 4][0] < d.values[length - 4][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD金叉3")
    if k.values[length - 1][0] > d.values[length - 1][0] and k.values[length - 2][0] > d.values[length - 2][0] and \
            k.values[length - 3][0] > d.values[length - 3][0] and k.values[length - 4][0] > d.values[length - 4][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD金叉n")
    if k.values[length - 1][0] < d.values[length - 1][0] and k.values[length - 2][0] > d.values[length - 2][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD死叉1")
    if k.values[length - 1][0] < d.values[length - 1][0] and k.values[length - 2][0] < d.values[length - 2][0] and \
            k.values[length - 3][0] > d.values[length - 3][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD死叉2")
    if k.values[length - 1][0] < d.values[length - 1][0] and k.values[length - 2][0] < d.values[length - 2][0] and \
            k.values[length - 3][0] < d.values[length - 3][0] and k.values[length - 4][0] > d.values[length - 4][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD死叉3")
    if k.values[length - 1][0] < d.values[length - 1][0] and k.values[length - 2][0] < d.values[length - 2][0] and \
            k.values[length - 3][0] < d.values[length - 3][0] and k.values[length - 4][0] < d.values[length - 4][0]:
        mark_stock_as_tag(stock_data, tag_prefix + "KD死叉n")