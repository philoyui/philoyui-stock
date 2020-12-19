package io.philoyui.stock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.stock.entity.MyStockEntity;

public interface MyStockDao extends GenericDao<MyStockEntity,Long> {

    MyStockEntity findBySymbol(String symbol);

}
