package io.philoyui.qmier.qmiermanager.controller;

import io.philoyui.qmier.qmiermanager.service.FinancialProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/admin/financial_product")
public class FinancialProductController {

    @Autowired
    private FinancialProductService financialProductService;

    @RequestMapping("/downloadHistory")
    public ResponseEntity<String> fetch() throws IOException {
        financialProductService.downloadHistoryData();
        return ResponseEntity.ok("success");
    }

}
