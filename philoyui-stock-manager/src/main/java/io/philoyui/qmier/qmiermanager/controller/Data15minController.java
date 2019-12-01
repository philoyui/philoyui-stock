package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.Data15minService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_15min")
public class Data15minController {

    @Autowired
    private Data15minService data15minService;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {

        data15minService.downloadHistory();

        return ResponseEntity.ok("success");
    }

}
