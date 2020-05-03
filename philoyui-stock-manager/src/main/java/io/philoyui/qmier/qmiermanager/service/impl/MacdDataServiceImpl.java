package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.MacdDataDao;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.MacdDataEntity;
import io.philoyui.qmier.qmiermanager.service.MacdDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MacdDataServiceImpl extends GenericServiceImpl<MacdDataEntity,Long> implements MacdDataService {

    @Autowired
    private MacdDataDao macdDataDao;

    @Override
    protected GenericDao<MacdDataEntity, Long> getDao() {
        return macdDataDao;
    }

    @Override
    public void deleteByIntervalType(IntervalType intervalType) {
        macdDataDao.deleteByIntervalType(intervalType);
    }

    @Override
    public List<MacdDataEntity> findBySymbol(String symbol) {
        return macdDataDao.findBySymbol(symbol);
    }
}
