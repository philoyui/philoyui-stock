package io.philoyui.stock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.stock.entity.FocusStockEntity;

public interface FocusStockDao extends GenericDao<FocusStockEntity,Long> {
    FocusStockEntity findBySymbol(String symbol);
}