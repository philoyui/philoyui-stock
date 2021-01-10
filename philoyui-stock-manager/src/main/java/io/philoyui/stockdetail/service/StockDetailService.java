package io.philoyui.stockdetail.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.stockdetail.entity.StockDetailEntity;

public interface StockDetailService extends GenericService<StockDetailEntity,Long> {
    StockDetailEntity findBySymbol(String symbol);
}
