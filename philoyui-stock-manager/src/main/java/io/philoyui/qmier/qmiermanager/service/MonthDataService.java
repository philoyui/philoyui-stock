package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.domain.StockHistoryData;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface MonthDataService extends GenericService<DataMonthEntity,Long> {

    void deleteBySymbol(String symbol);

    void insertAll(List<DataMonthEntity> dataMonthEntityList);

    StockHistoryData findStockHistoryData(StockEntity stockEntity);

    void deleteAll();

    void downloadHistory(StockEntity stockEntity);
}
