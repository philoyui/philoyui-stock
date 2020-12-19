package io.philoyui.stock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.stock.entity.StockEntity;

public interface StockDao extends GenericDao<StockEntity,Long> {

    StockEntity findBySymbol(String symbol);

}