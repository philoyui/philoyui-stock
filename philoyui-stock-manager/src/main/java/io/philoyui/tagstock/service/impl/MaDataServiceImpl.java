package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.tagstock.dao.MaDataDao;
import io.philoyui.tagstock.entity.MaDataEntity;
import io.philoyui.tagstock.service.MaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class MaDataServiceImpl extends GenericServiceImpl<MaDataEntity,Long> implements MaDataService {

    @Autowired
    private MaDataDao maDataDao;

    @Override
    protected GenericDao<MaDataEntity, Long> getDao() {
        return maDataDao;
    }

    @Transactional
    @Override
    public void deleteDayData() {
        maDataDao.deleteData("Day");
    }
}
