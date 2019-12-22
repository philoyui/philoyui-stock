package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.TimerTaskEntity;
import io.philoyui.qmier.qmiermanager.service.TimerTaskService;
import io.philoyui.qmier.qmiermanager.timer.TimeScheduler;
import io.philoyui.qmier.qmiermanager.timer.TimeSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/timer_task")
public class TimerTaskController {

    @Autowired
    private TimerTaskService timerTaskService;

    @Autowired
    private TimeSchedulerFactory timeSchedulerFactory;

    @RequestMapping("/execute")
    public ResponseEntity<String> execute(@RequestParam Long id) {
        TimerTaskEntity timerTaskEntity = timerTaskService.get(id);
        TimeScheduler timeScheduler = timeSchedulerFactory.findByBeanName(timerTaskEntity.getBeanName());
        timeScheduler.schedule();
        return ResponseEntity.ok("success");
    }
}
