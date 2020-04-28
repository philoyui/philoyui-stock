package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.domain.DayData;
import io.philoyui.qmier.qmiermanager.domain.StockHistoryData;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface DayDataService extends GenericService<DataDayEntity,Long> {

    void insertAll(List<DataDayEntity> dataDayEntityList);

    void deleteBySymbol(String symbol);

    void downloadHistory();

    void processEstimateDayData();

    StockHistoryData findStockHistoryData(StockEntity stockEntity);

    void downloadHistory(StockEntity stockEntity);
}
