package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/定时器任务")
public class TimerTaskController {

    @Autowired
    private TimerTaskService timerTaskService;

    @RequestMapping("/hello")
    public ResponseEntity<String> fetch(@RequestParam Long id) {
        return ResponseEntity.ok("success");
    }
}
