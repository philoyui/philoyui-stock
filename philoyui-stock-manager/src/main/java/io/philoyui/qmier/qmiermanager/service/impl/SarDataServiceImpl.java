package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.SarDataDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.SarDataEntity;
import io.philoyui.qmier.qmiermanager.service.SarDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class SarDataServiceImpl extends GenericServiceImpl<SarDataEntity,Long> implements SarDataService {

    @Autowired
    private SarDataDao sarDataDao;

    @Override
    protected GenericDao<SarDataEntity, Long> getDao() {
        return sarDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        sarDataDao.deleteData("Day");
    }
}
