package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.StockIndicatorEntity;

import java.util.List;

public interface StockIndicatorDao extends GenericDao<StockIndicatorEntity,Long> {

    List<StockIndicatorEntity> findByEnable(Boolean enable);

}
