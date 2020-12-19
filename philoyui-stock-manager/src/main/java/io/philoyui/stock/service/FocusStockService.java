package io.philoyui.stock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.stock.entity.FocusStockEntity;

public interface FocusStockService extends GenericService<FocusStockEntity,Long> {
    FocusStockEntity findBySymbol(String symbol);

    void deleteAll();
}
