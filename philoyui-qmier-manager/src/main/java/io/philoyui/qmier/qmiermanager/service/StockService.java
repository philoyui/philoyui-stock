package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.stock.StockEntity;

import java.util.List;

public interface StockService extends GenericService<StockEntity,Long> {
    void insertAll(List<StockEntity> stockEntity);
}
