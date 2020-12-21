package io.philoyui.focus.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.focus.entity.FocusStockEntity;

public interface FocusStockDao extends GenericDao<FocusStockEntity,Long> {
    FocusStockEntity findBySymbol(String symbol);
}