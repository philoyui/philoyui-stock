from base import mark_stock_as_tag
'''
    3倍量
    参数
        stock_data：股票信息 stock_data[5] 股票代码 stock_data[4] 股票名称
        data_frame：股票历史数据，data_frame['close']，data_frame['low']，data_frame['high']，data_frame['open']，data_frame['volume']
        tag_prefix：标签名字前缀（日线，周线，月线）
'''


def process_volume(stock_data, data_frame, tag_prefix):
    volume = data_frame['volume'].values
    open = data_frame['open'].values
    close = data_frame['close'].values

    if volume[-1] > (volume[-2]*3) and -0.06 < ((open[-1]-close[-1])/open[-1]) < 0.06:
        mark_stock_as_tag(stock_data, "3倍量")
