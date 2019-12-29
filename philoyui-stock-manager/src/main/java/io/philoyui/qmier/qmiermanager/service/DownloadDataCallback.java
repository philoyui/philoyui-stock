package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.to.KLineData;

public interface DownloadDataCallback {

    void process(StockEntity stockEntity, KLineData[] KLineDataArray);

}
