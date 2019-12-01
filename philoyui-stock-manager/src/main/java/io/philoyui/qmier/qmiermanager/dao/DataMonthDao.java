package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;

public interface DataMonthDao extends GenericDao<DataMonthEntity,Long> {
    void deleteBySymbol(String symbol);
}