package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.DataHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_hour")
public class DataHourController {

    @Autowired
    private DataHourService dataHourService;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {

        dataHourService.downloadHistory();

        return ResponseEntity.ok("success");
    }

}
