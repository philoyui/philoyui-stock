package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.RsiDataDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.RsiDataEntity;
import io.philoyui.qmier.qmiermanager.service.RsiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class RsiDataServiceImpl extends GenericServiceImpl<RsiDataEntity,Long> implements RsiDataService {

    @Autowired
    private RsiDataDao rsiDataDao;

    @Override
    protected GenericDao<RsiDataEntity, Long> getDao() {
        return rsiDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        rsiDataDao.deleteData("Day");
    }
}
