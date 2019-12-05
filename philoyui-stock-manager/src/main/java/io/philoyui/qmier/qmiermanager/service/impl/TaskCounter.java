package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.entity.TimerTaskEntity;
import io.philoyui.qmier.qmiermanager.service.TimerTaskService;

import java.util.concurrent.atomic.AtomicLong;

public class TaskCounter {

    private AtomicLong counter = new AtomicLong(0);

    private TimerTaskService timerTaskService;

    private TimerTaskEntity newTaskEntity;

    public TaskCounter(TimerTaskService timerTaskService, TimerTaskEntity newTaskEntity) {
        this.timerTaskService = timerTaskService;
        this.newTaskEntity = newTaskEntity;
    }

    public void increase() {
        long currentCount = counter.incrementAndGet();
        if(currentCount%10==0){
            newTaskEntity.setCompleteCount(currentCount);
            timerTaskService.update(newTaskEntity);
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
