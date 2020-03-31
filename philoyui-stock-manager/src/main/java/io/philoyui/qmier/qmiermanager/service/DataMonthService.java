package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface DataMonthService extends GenericService<DataMonthEntity,Long> {

    void deleteBySymbol(String symbol);

    void insertAll(List<DataMonthEntity> dataMonthEntityList);

    void downloadHistory();

    double[] findLowData(StockEntity stockEntity);

    double[] findHighData(StockEntity stockEntity);

    double[] findCloseData(StockEntity stockEntity);

    double[] findOpenData(StockEntity stockEntity);

    double[] findVolumeData(StockEntity stockEntity);
}
