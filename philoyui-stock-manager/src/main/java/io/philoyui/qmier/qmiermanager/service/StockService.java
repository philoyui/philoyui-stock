package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface StockService extends GenericService<StockEntity,Long> {

    StockEntity findBySymbol(String symbol);

    void enable(Long id);

    void disable(Long id);

    List<StockEntity> findEnable();

    void downloadHistoryData();

    void updateAll(List<StockEntity> stockEntityList);

    void allEnable();

    List<StockEntity> findAll();

}
