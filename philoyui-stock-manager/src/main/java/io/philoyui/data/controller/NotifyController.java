package io.philoyui.data.controller;

import io.philoyui.stock.service.StockService;
import io.philoyui.weixin.service.MailService;
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

    @Autowired
    private MailService mailService;

    @RequestMapping("/alert")
    public ResponseEntity<String> alert(@RequestParam String symbol,@RequestParam String msg){

        String stockName = stockService.findStockName(symbol);

        mailService.sendSimpleMail("yang7551735@126.com","发现背离",stockName + " " + symbol +  "" + msg);

        return ResponseEntity.ok("success");
    }

}
