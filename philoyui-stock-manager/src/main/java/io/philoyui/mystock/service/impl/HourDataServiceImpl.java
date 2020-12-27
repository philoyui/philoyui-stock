package io.philoyui.mystock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.mystock.dao.HourDataDao;
import io.philoyui.mystock.entity.HourDataEntity;
import io.philoyui.mystock.service.HourDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HourDataServiceImpl extends GenericServiceImpl<HourDataEntity,Long> implements HourDataService {

    @Autowired
    private HourDataDao hourDataDao;

    @Override
    protected GenericDao<HourDataEntity, Long> getDao() {
        return hourDataDao;
    }

    @Override
    public void insertAll(List<HourDataEntity> hourDataEntityList) {
        hourDataDao.saveAll(hourDataEntityList);
    }

    @Override
    public void deleteAll() {
        hourDataDao.deleteAll();
    }
}
