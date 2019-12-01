package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.DataHourEntity;

public interface DataHourDao extends GenericDao<DataHourEntity,Long> {
    void deleteBySymbol(String symbol);
}