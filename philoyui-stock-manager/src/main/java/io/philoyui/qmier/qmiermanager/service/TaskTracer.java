package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.impl.TaskExecutor;

public interface TaskTracer {


    /**
     * 任务执行器
     * @param taskType          任务类型
     * @param taskExecutor      任务执行器
     */
    void trace(TaskType taskType, TaskExecutor taskExecutor);

}
