package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.page.excp.GmosException;
import io.philoyui.qmier.qmiermanager.entity.TimerTaskEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TaskTracer {

    @Autowired
    private TimerTaskService timerTaskService;

    public void trace(TaskType taskType, TaskExecutor taskExecutor) {
        boolean success = true;
        String executeResult = "成功";
        TimerTaskEntity newTaskEntity = startTimerTask(taskType);
        TaskCounter taskCounter = new TaskCounter(timerTaskService,newTaskEntity);
        try {
            taskExecutor.execute(taskCounter);
        }catch (GmosException e){
            success=false;
            executeResult = e.getMessage();
        }finally {
            endTimerTask(newTaskEntity,taskCounter, success, executeResult);
        }
    }

    private void endTimerTask(TimerTaskEntity newTaskEntity, TaskCounter taskCounter, boolean success, String executeResult) {
        long endTimeMillis = System.currentTimeMillis();
        newTaskEntity.setEndTime(new Date(endTimeMillis));
        newTaskEntity.setPeriodMinute((endTimeMillis-newTaskEntity.getStartTime().getTime())/60000);
        newTaskEntity.setSuccess(success);
        newTaskEntity.setExecuteResult(executeResult);
        newTaskEntity.setCompleteCount(taskCounter.getCompleteCount());
        timerTaskService.update(newTaskEntity);
    }

    private TimerTaskEntity startTimerTask(TaskType taskType) {
        long startTimeMillis = System.currentTimeMillis();
        TimerTaskEntity timerTask = new TimerTaskEntity();
        timerTask.setName(taskType.getTaskName());
        timerTask.setStartTime(new Date(startTimeMillis));
        timerTask.setCompleteCount(0L);
        timerTask.setTaskType(taskType);
        return timerTaskService.insert(timerTask);
    }
}
