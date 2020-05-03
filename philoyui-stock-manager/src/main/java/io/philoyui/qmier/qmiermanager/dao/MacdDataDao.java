package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.MacdDataEntity;

import java.util.List;

public interface MacdDataDao extends GenericDao<MacdDataEntity,Long> {

    void deleteByIntervalType(IntervalType intervalType);

    List<MacdDataEntity> findBySymbol(String symbol);

}
