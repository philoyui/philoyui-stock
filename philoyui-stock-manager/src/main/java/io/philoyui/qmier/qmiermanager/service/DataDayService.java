package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface DataDayService extends GenericService<DataDayEntity,Long> {

    void insertAll(List<DataDayEntity> dataDayEntityList);

    void deleteBySymbol(String symbol);

    void downloadHistory();

    void processEstimateDayData();

    double[] findCloseData(StockEntity stockEntity);

    double[] findVolumeData(StockEntity stockEntity);

    double[] findOpenData(StockEntity stockEntity);
}
