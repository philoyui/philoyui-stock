package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.DataDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_day")
public class DataDayController {

    @Autowired
    private DataDayService dataDayService;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {

        dataDayService.downloadHistory();

        return ResponseEntity.ok("success");
    }
}
