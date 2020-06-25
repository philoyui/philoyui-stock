package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.TradingViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/admin/trading_view")
public class TradingViewController {

    @Autowired
    private TradingViewService tradingViewService;

    @RequestMapping("/fetch")
    public ResponseEntity<String> fetch(@RequestParam Long id) {
        tradingViewService.fetchCurrent();
        return ResponseEntity.ok("success");
    }
}
