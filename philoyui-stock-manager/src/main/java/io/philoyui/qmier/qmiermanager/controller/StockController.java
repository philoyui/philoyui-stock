package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/hello")
    public ResponseEntity<String> fetch(@RequestParam Long id) {
        return ResponseEntity.ok("success");
    }
}
