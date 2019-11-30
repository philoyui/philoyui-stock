package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface StockService extends GenericService<StockEntity,Long> {
    void insertAll(List<StockEntity> asList);
}
