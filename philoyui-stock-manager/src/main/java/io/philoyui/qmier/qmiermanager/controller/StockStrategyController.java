package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/stock_strategy")
public class StockStrategyController {

    @Autowired
    private StockStrategyService stockStrategyService;

    @RequestMapping("/tagStock")
    public ResponseEntity<String> filter(Long id) {

        StockStrategyEntity stockStrategyEntity = stockStrategyService.get(id);

        stockStrategyService.tagStock(stockStrategyEntity);

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/dropStock")
    public ResponseEntity<String> dropStock(Long id) {

        StockStrategyEntity stockStrategyEntity = stockStrategyService.get(id);

        stockStrategyService.dropStock(stockStrategyEntity);

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/weekTask")
    public ResponseEntity<String> weekTask(Long id) {

        stockStrategyService.processWithWeekTimer();

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/dayTask")
    public ResponseEntity<String> dayTask(Long id) {

        stockStrategyService.processWithDayTimer();

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/monthTask")
    public ResponseEntity<String> monthTask(Long id) {

        stockStrategyService.processWithMonthTimer();

        return ResponseEntity.ok("success");
    }

}
