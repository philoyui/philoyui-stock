package io.philoyui.tradingview.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.tradingview.entity.TradingViewEntity;

public interface TradingViewDao extends GenericDao<TradingViewEntity,Long> {
    TradingViewEntity findBySymbol(String symbol);
}