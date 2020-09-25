package io.philoyui.qmier.qmiermanager.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.tagstock.dao.BollDataDao;
import io.philoyui.qmier.qmiermanager.tagstock.entity.BollDataEntity;
import io.philoyui.qmier.qmiermanager.tagstock.service.BollDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BollDataServiceImpl extends GenericServiceImpl<BollDataEntity,Long> implements BollDataService {

    @Autowired
    private BollDataDao bollDataDao;

    @Override
    protected GenericDao<BollDataEntity, Long> getDao() {
        return bollDataDao;
    }

}
