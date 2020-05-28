package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.KdjDataDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.KdjDataEntity;
import io.philoyui.qmier.qmiermanager.service.KdjDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class KdjDataServiceImpl extends GenericServiceImpl<KdjDataEntity,Long> implements KdjDataService {

    @Autowired
    private KdjDataDao kdjDataDao;

    @Override
    protected GenericDao<KdjDataEntity, Long> getDao() {
        return kdjDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        kdjDataDao.deleteData("Day");
    }

    @Override
    public void deleteMonthData() {
        kdjDataDao.deleteData("Month");
    }

    @Override
    public void deleteWeekData() {
        kdjDataDao.deleteData("Week");
    }
}
