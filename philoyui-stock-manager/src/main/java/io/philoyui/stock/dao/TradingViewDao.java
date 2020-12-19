package io.philoyui.stock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.stock.entity.TradingViewEntity;

public interface TradingViewDao extends GenericDao<TradingViewEntity,Long> {
    TradingViewEntity findBySymbol(String symbol);
}