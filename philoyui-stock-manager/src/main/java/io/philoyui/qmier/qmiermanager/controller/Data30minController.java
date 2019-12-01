package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.Data30minService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/data_30min")
public class Data30minController {

    @Autowired
    private Data30minService data30minService;

    @RequestMapping("/download_history")
    public ResponseEntity<String> downloadHistory() {
        data30minService.downloadHistory();
        return ResponseEntity.ok("success");
    }

}
