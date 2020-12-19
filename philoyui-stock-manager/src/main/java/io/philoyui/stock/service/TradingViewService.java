package io.philoyui.stock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.stock.entity.TradingViewEntity;

import java.util.List;

public interface TradingViewService extends GenericService<TradingViewEntity,Long> {

    void batchInsert(List<TradingViewEntity> tradingViewEntities);

    void fetchCurrent();

    TradingViewEntity findBySymbol(String symbol);

}
