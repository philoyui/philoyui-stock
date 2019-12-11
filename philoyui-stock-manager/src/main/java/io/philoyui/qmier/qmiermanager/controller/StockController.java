package io.philoyui.qmier.qmiermanager.controller;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.FinancialReport;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClient;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClientImpl;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueqiuList;
import io.philoyui.qmier.qmiermanager.client.xueqiu.request.FinancialReportRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.FinancialReportResponse;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/fetch")
    public ResponseEntity<String> fetch() throws IOException {

        XueQiuClient client = new XueQiuClientImpl();

        List<StockEntity> stockLists = stockService.list(SearchFilter.getDefault());

        for (StockEntity stockList : stockLists) {

        }

        for (int i = 1; i < 10; i++) {
            FinancialReportRequest request = new FinancialReportRequest();
            request.setSymbol("");
            FinancialReportResponse response = client.execute(request);

            List<FinancialReport> financialReports = new ArrayList<>();
            for (XueqiuList xueqiuList : response.getData().getList()) {
            }
        }

        return ResponseEntity.ok("success");
    }
}
