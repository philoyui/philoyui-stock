package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DayDataDao;
import io.philoyui.qmier.qmiermanager.entity.DayDataEntity;
import io.philoyui.qmier.qmiermanager.service.DayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DayDataServiceImpl extends GenericServiceImpl<DayDataEntity,Long> implements DayDataService {

    @Autowired
    private DayDataDao dayDataDao;

    @Override
    protected GenericDao<DayDataEntity, Long> getDao() {
        return dayDataDao;
    }

}