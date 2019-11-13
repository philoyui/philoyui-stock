package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.DayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/dayData")
public class DayDataController {

    @Autowired
    private DayDataService dayDataService;

    @RequestMapping("/hello")
    public ResponseEntity<String> fetch(@RequestParam Long id) {
        return ResponseEntity.ok("success");
    }
}
