package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.MaDataDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.MaDataEntity;
import io.philoyui.qmier.qmiermanager.service.MaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MaDataServiceImpl extends GenericServiceImpl<MaDataEntity,Long> implements MaDataService {

    @Autowired
    private MaDataDao maDataDao;

    @Override
    protected GenericDao<MaDataEntity, Long> getDao() {
        return maDataDao;
    }

    @Override
    public void deleteDayData() {
        maDataDao.deleteData("Day");
    }
}
