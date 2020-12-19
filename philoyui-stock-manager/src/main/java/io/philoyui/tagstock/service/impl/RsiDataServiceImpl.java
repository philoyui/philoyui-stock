package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.tagstock.dao.RsiDataDao;
import io.philoyui.tagstock.entity.RsiDataEntity;
import io.philoyui.tagstock.service.RsiDataService;
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

    @Transactional
    @Override
    public void deleteMonthData() {
        rsiDataDao.deleteData("Month");
    }

    @Transactional
    @Override
    public void deleteWeekData() {
        rsiDataDao.deleteData("Week");
    }
}
