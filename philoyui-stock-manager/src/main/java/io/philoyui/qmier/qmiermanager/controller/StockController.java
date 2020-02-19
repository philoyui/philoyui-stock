package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/downloadHistory")
    public ResponseEntity<String> fetch() throws IOException {
        stockService.downloadHistoryData();
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/allEnable")
    public ResponseEntity<String> allEnable() throws IOException {
        stockService.allEnable();
        return ResponseEntity.ok("success");
    }

}
