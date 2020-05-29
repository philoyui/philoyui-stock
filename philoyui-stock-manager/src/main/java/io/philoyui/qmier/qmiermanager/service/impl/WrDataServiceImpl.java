package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.WrDataDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.WrDataEntity;
import io.philoyui.qmier.qmiermanager.service.WrDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class WrDataServiceImpl extends GenericServiceImpl<WrDataEntity,Long> implements WrDataService {

    @Autowired
    private WrDataDao wrDataDao;

    @Override
    protected GenericDao<WrDataEntity, Long> getDao() {
        return wrDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        wrDataDao.deleteData("Day");
    }

    @Transactional
    @Override
    public void deleteMonthData() {
        wrDataDao.deleteData("Month");
    }

    @Transactional
    @Override
    public void deleteWeekData() {
        wrDataDao.deleteData("Week");
    }
}
