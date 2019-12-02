package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/task_type")
public class TaskTypeController {

    @Autowired
    private TaskTypeService taskTypeService;

    @RequestMapping("/hello")
    public ResponseEntity<String> fetch(@RequestParam Long id) {
        return ResponseEntity.ok("success");
    }
}
