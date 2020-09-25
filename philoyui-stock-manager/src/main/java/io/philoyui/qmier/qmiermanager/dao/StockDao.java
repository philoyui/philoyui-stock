package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.util.List;

public interface StockDao extends GenericDao<StockEntity,Long> {

    StockEntity findBySymbol(String symbol);

}