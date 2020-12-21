package io.philoyui.mystock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.mystock.entity.MyStockEntity;

public interface MyStockDao extends GenericDao<MyStockEntity,Long> {

    MyStockEntity findBySymbol(String symbol);

}
