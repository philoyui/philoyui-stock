package io.philoyui.stock.controller;


import io.philoyui.stock.service.StockService;
import io.philoyui.stock.timer.StockFetchTimer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @Autowired
    private StockFetchTimer stockFetchTimer;

    @RequestMapping("/addMyStock")
    public ResponseEntity<String> fetch(Long id) {
        stockService.addToMyStock(id);
        return ResponseEntity.ok("success");
    }

    @RequestMapping("/reload")
    public ResponseEntity<String> reload() {
        stockFetchTimer.execute();
        return ResponseEntity.ok("success");
    }

}
