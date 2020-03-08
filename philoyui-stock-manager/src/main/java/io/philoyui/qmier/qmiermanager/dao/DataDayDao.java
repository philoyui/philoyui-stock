package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;

public interface DataDayDao extends GenericDao<DataDayEntity,Long> {

    void deleteBySymbol(String symbol);

    long countByDateString(String dataString);

}
