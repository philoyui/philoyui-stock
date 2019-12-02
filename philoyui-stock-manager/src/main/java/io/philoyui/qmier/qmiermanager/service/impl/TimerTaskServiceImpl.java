package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.TimerTaskDao;
import io.philoyui.qmier.qmiermanager.entity.TimerTaskEntity;
import io.philoyui.qmier.qmiermanager.service.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskServiceImpl extends GenericServiceImpl<TimerTaskEntity,Long> implements TimerTaskService {

    @Autowired
    private TimerTaskDao timerTaskDao;

    @Override
    protected GenericDao<TimerTaskEntity, Long> getDao() {
        return timerTaskDao;
    }

}