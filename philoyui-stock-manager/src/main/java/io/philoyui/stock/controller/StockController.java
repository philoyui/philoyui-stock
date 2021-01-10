package io.philoyui.stock.controller;


import io.philoyui.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/addMyStock")
    public ResponseEntity<String> fetch(Long id) {
        stockService.addToMyStock(id);
        return ResponseEntity.ok("success");
    }

}
