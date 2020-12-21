package io.philoyui.focus.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.focus.entity.FocusStockEntity;

public interface FocusStockService extends GenericService<FocusStockEntity,Long> {
    FocusStockEntity findBySymbol(String symbol);

    void deleteAll();

    void addToMyStock(Long id);
}
