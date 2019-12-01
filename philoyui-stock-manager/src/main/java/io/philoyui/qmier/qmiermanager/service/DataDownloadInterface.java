package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.to.HistoryData;

public interface DataDownloadInterface {

    void process(HistoryData[] historyDataArray, FinancialProductEntity financialProductEntity);

}
