package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.TimerTaskEntity;
import io.philoyui.qmier.qmiermanager.service.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/timer_task")
public class TimerTaskController {

    @Autowired
    private TimerTaskService timerTaskService;

    @RequestMapping("/execute")
    public ResponseEntity<String> execute(@RequestParam Long id) {

        TimerTaskEntity timerTaskEntity = timerTaskService.get(id);




        return ResponseEntity.ok("success");
    }
}
