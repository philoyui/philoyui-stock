package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.to.KLineData;

public interface DownloadDataCallback {

    void process(FinancialProductEntity financialProductEntity, KLineData[] KLineDataArray);

}
