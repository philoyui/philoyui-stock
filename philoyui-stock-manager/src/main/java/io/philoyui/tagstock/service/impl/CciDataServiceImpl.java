package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.tagstock.dao.CciDataDao;
import io.philoyui.tagstock.entity.CciDataEntity;
import io.philoyui.tagstock.service.CciDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class CciDataServiceImpl extends GenericServiceImpl<CciDataEntity,Long> implements CciDataService {

    @Autowired
    private CciDataDao cciDataDao;

    @Override
    protected GenericDao<CciDataEntity, Long> getDao() {
        return cciDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        cciDataDao.deleteData("Day");
    }

    @Transactional
    @Override
    public void deleteWeekData() {
        cciDataDao.deleteData("Week");
    }

    @Transactional
    @Override
    public void deleteMonthData() {
        cciDataDao.deleteData("Month");
    }
}
