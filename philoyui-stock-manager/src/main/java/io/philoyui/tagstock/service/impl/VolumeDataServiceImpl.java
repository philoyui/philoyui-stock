package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.tagstock.dao.VolumeDataDao;
import io.philoyui.tagstock.entity.VolumeDataEntity;
import io.philoyui.tagstock.service.VolumeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class VolumeDataServiceImpl extends GenericServiceImpl<VolumeDataEntity,Long> implements VolumeDataService {

    @Autowired
    private VolumeDataDao volumeDataDao;

    @Override
    protected GenericDao<VolumeDataEntity, Long> getDao() {
        return volumeDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        volumeDataDao.deleteData("Day");
    }

    @Transactional
    @Override
    public void deleteWeekData() {
        volumeDataDao.deleteData("Week");
    }

    @Transactional
    @Override
    public void deleteMonthData() {
        volumeDataDao.deleteData("Month");
    }
}
