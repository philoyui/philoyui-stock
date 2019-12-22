package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.TimerTaskLogDao;
import io.philoyui.qmier.qmiermanager.entity.TimerTaskLogEntity;
import io.philoyui.qmier.qmiermanager.service.TimerTaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskLogServiceImpl extends GenericServiceImpl<TimerTaskLogEntity,Long> implements TimerTaskLogService {

    @Autowired
    private TimerTaskLogDao timerTaskLogDao;

    @Override
    protected GenericDao<TimerTaskLogEntity, Long> getDao() {
        return timerTaskLogDao;
    }

}