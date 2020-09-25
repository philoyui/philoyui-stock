package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;

public interface MyStockDao extends GenericDao<MyStockEntity,Long> {

    MyStockEntity findBySymbol(String symbol);

}
