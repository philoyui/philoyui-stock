package io.philoyui.stockdetail.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.stockdetail.entity.StockDetailEntity;

/**
 * @author DELL
 */
public interface StockDetailDao extends GenericDao<StockDetailEntity,Long> {
    StockDetailEntity findBySymbol(String symbol);
}
