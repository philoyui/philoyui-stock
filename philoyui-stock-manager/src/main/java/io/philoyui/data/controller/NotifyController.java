package io.philoyui.data.controller;

import io.philoyui.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/notify")
public class NotifyController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/alert")
    public ResponseEntity<String> alert(@RequestParam String symbol,@RequestParam String msg){

        String stockName = stockService.findStockName(symbol);

        System.out.println(stockName + " " + symbol +  "" + msg);

        return ResponseEntity.ok("success");
    }

}
