package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.stock.StockEntity;

public interface StockService extends GenericService<StockEntity,Long> {
    void insertAll(StockEntity[] stockEntity);
}
