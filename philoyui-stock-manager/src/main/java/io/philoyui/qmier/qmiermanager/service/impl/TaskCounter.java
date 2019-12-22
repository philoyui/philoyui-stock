package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.entity.TimerTaskLogEntity;
import io.philoyui.qmier.qmiermanager.service.TimerTaskLogService;

import java.util.concurrent.atomic.AtomicLong;

public class TaskCounter {

    private AtomicLong counter = new AtomicLong(0);

    private TimerTaskLogService timerTaskLogService;

    private TimerTaskLogEntity newTaskEntity;

    public TaskCounter(TimerTaskLogService timerTaskLogService, TimerTaskLogEntity newTaskEntity) {
        this.timerTaskLogService = timerTaskLogService;
        this.newTaskEntity = newTaskEntity;
    }

    public void increase() {
        long currentCount = counter.incrementAndGet();
        if(currentCount%10==0){
            newTaskEntity.setCompleteCount(currentCount);
            timerTaskLogService.update(newTaskEntity);
        }
    }

    /**
     * 完成数
     * @return
     */
    public Long getCompleteCount() {
        return counter.get();
    }
}
