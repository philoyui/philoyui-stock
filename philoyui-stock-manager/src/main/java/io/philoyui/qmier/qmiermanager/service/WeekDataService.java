package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.DataWeekEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface WeekDataService extends GenericService<DataWeekEntity,Long> {

    void insertAll(List<DataWeekEntity> dataWeekEntityList);

    void deleteBySymbol(String symbol);

    void downloadHistory();

    double[] findLowData(StockEntity stockEntity);

    double[] findHighData(StockEntity stockEntity);

    double[] findCloseData(StockEntity stockEntity);

    double[] findOpenData(StockEntity stockEntity);

    double[] findVolumeData(StockEntity stockEntity);
}
