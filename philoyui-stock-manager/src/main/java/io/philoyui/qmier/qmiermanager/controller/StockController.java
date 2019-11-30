package io.philoyui.qmier.qmiermanager.controller;

import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClient;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClientImpl;
import io.philoyui.qmier.qmiermanager.client.xueqiu.request.AnnualReportRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.AnnualReportResponse;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/fetch")
    public ResponseEntity<String> fetch() throws IOException {

        XueQiuClient client = new XueQiuClientImpl();

        for (int i = 1; i < 10; i++) {
            AnnualReportRequest request = new AnnualReportRequest();
            request.setPage(i);
            request.setSize(500);
            request.setOrder("desc");
            request.setOrderBy("percent");
            request.setMarket("CN");
            request.setType("sh_sz");

            AnnualReportResponse response = client.execute(request);

            stockService.insertAll(Arrays.asList(response.getData().getList()));

        }

        return ResponseEntity.ok("success");
    }
}
