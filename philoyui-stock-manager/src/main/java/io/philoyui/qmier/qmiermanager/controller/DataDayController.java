package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.DayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_day")
public class DataDayController {

    @Autowired
    private DayDataService dayDataService;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {

        dayDataService.downloadHistory();

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/download_today")
    public ResponseEntity<String> downloadToday() {

        dayDataService.processEstimateDayData();

        return ResponseEntity.ok("success");
    }
}
