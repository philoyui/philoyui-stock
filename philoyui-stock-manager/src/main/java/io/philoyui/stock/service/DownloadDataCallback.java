package io.philoyui.stock.service;

import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.stock.to.KLineData;

public interface DownloadDataCallback {

    void process(MyStockEntity stockEntity, KLineData[] KLineDataArray);

}
