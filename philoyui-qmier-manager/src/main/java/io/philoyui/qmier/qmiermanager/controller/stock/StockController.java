package io.philoyui.qmier.qmiermanager.controller.stock;

import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClient;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClientImpl;
import io.philoyui.qmier.qmiermanager.client.xueqiu.request.AnnualReportRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.AnnualReportResponse;
import io.philoyui.qmier.qmiermanager.entity.stock.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/admin/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping("/fetch")
    public ResponseEntity<String> fetch() throws IOException {


        XueQiuClient client = new XueQiuClientImpl();

        AnnualReportRequest request = new AnnualReportRequest();
        request.setPage(1);
        request.setSize(5000);
        request.setOrder("desc");
        request.setOrderBy("percent");
        request.setMarket("CN");
        request.setType("sh_sz");

        AnnualReportResponse response = client.execute(request);

        for (StockEntity stockEntity : response.getData().getList()) {
            stockService.insert(stockEntity);
        }

        return ResponseEntity.ok("success");
    }
}
