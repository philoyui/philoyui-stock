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
    public ResponseEntity<String> alert(
            @RequestParam String symbol,
            @RequestParam String close1,
            @RequestParam String close2,
            @RequestParam String macd1,
            @RequestParam String macd2,
            @RequestParam String type,
            @RequestParam String intervalType
        ){

        String stockName = stockService.findStockName(symbol);

        StringBuilder stringBuilder = new StringBuilder();
        if("1" .equalsIgnoreCase(intervalType)){
            stringBuilder.append("发现底背离：");
        }else{
            stringBuilder.append("发现顶背离：");
        }
        stringBuilder.append(stockName).append("(").append(symbol).append(")");
        stringBuilder.append("close1=").append(close1).append(",").append("close2=").append(close2).append(";");
        stringBuilder.append("macd1=").append(macd1).append(",").append("macd2=").append(macd2);

        mailService.sendSimpleMail("yang7551735@126.com","发现背离",stringBuilder.toString());

        return ResponseEntity.ok("success");
    }

}
