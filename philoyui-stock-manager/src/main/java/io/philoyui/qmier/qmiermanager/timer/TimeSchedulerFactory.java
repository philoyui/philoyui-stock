package io.philoyui.qmier.qmiermanager.timer;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;

@Component
public class TimeSchedulerFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public TimeScheduler findByBeanName(String identify) {
        return  (TimeScheduler)applicationContext.getBean(identify);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
