package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.page.excp.GmosException;
import io.philoyui.qmier.qmiermanager.entity.TimerTaskLogEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.TaskTracer;
import io.philoyui.qmier.qmiermanager.service.TimerTaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskTracerImpl implements TaskTracer {

    @Autowired
    private TimerTaskLogService timerTaskLogService;

    @Override
    public void trace(TaskType taskType, TaskExecutor taskExecutor) {
        boolean success = true;
        String executeResult = "成功";
        TimerTaskLogEntity newTaskEntity = startTimerTask(taskType);
        TaskCounter taskCounter = new TaskCounter(timerTaskLogService,newTaskEntity);
        try {
            taskExecutor.execute(taskCounter);
        }catch (GmosException e){
            success=false;
            executeResult = e.getMessage();
        }finally {
            endTimerTask(newTaskEntity,taskCounter, success, executeResult);
        }
    }

    private void endTimerTask(TimerTaskLogEntity newTaskEntity, TaskCounter taskCounter, boolean success, String executeResult) {
        long endTimeMillis = System.currentTimeMillis();
        newTaskEntity.setEndTime(new Date(endTimeMillis));
        newTaskEntity.setPeriodMinute((endTimeMillis-newTaskEntity.getStartTime().getTime())/60000);
        newTaskEntity.setSuccess(success);
        newTaskEntity.setExecuteResult(executeResult);
        newTaskEntity.setCompleteCount(taskCounter.getCompleteCount());
        timerTaskLogService.update(newTaskEntity);
    }

    /**
     * 标记定时任务已启动
     * @param taskType
     * @return
     */
    private TimerTaskLogEntity startTimerTask(TaskType taskType) {
        long startTimeMillis = System.currentTimeMillis();
        TimerTaskLogEntity timerTask = new TimerTaskLogEntity();
        timerTask.setName(taskType.getTaskName());
        timerTask.setStartTime(new Date(startTimeMillis));
        timerTask.setCompleteCount(0L);
        timerTask.setTaskType(taskType);
        return timerTaskLogService.insert(timerTask);
    }
}
