package io.philoyui.stock.service;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.to.KLineData;

public interface DownloadDataCallback {

    void process(StockEntity stockEntity, KLineData[] KLineDataArray);

}
