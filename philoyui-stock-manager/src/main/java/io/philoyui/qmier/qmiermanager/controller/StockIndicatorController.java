package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.StockIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/stock_indicator")
public class StockIndicatorController {

    @Autowired
    private StockIndicatorService stockIndicatorService;

    @RequestMapping("/executeGlobal")
    public ResponseEntity<String> executeGlobal(Long id) {

        stockIndicatorService.executeGlobal(id);

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/weekTask")
    public ResponseEntity<String> weekTask() {

        stockIndicatorService.executeWeekTask();

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/dayTask")
    public ResponseEntity<String> dayTask() {

        stockIndicatorService.executeDayTask();

        return ResponseEntity.ok("success");
    }

    @RequestMapping("/monthTask")
    public ResponseEntity<String> monthTask(Long id) {

        stockIndicatorService.executeWeekTask();

        return ResponseEntity.ok("success");
    }

}
