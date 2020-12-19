package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.tagstock.dao.BollDataDao;
import io.philoyui.tagstock.entity.BollDataEntity;
import io.philoyui.tagstock.service.BollDataService;
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
