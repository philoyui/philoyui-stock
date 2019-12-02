package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.TaskTypeDao;
import io.philoyui.qmier.qmiermanager.entity.TaskTypeEntity;
import io.philoyui.qmier.qmiermanager.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskTypeServiceImpl extends GenericServiceImpl<TaskTypeEntity,Long> implements TaskTypeService {

    @Autowired
    private TaskTypeDao taskTypeDao;

    @Override
    protected GenericDao<TaskTypeEntity, Long> getDao() {
        return taskTypeDao;
    }

}