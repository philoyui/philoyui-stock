package io.philoyui.stock.controller;

import io.philoyui.stock.service.TradingViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/trading_view")
public class TradingViewController {

    @Autowired
    private TradingViewService tradingViewService;

    @RequestMapping("/fetch")
    public ResponseEntity<String> fetch() {
        tradingViewService.fetchCurrent();
        return ResponseEntity.ok("success");
    }
}
