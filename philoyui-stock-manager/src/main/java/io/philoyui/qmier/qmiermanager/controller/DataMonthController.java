package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.DataMonthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_month")
public class DataMonthController {

    @Autowired
    private DataMonthService dataMonthService;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        dataMonthService.downloadHistory();
        return ResponseEntity.ok("success");
    }
}
