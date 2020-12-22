package io.philoyui.stock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.stock.entity.StockEntity;

import java.util.List;

public interface StockService extends GenericService<StockEntity,Long> {

    StockEntity findBySymbol(String symbol);

    void enable(Long id);

    void disable(Long id);

    List<StockEntity> findEnable();

    List<StockEntity> findAll();

    void fetchProductDataArray(String identifier,int pageSize);

    String findStockName(String symbol);

    void addToMyStock(Long id);
}
