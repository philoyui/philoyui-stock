package io.philoyui.qmier.qmiermanager.controller.stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.StockResponse;
import io.philoyui.qmier.qmiermanager.entity.stock.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

        List<String> elements = new ArrayList<>();
        elements.add("f14");
        elements.add("f12");
        elements.add("f2");
        elements.add("f3");
        elements.add("f4");
        elements.add("f5");
        elements.add("f6");
        elements.add("f20");
        elements.add("f7");
        elements.add("f15");
        elements.add("f16");
        elements.add("f14");
        elements.add("f17");
        elements.add("f18");
        elements.add("f10");
        elements.add("f8");
        elements.add("f9");
        elements.add("f23");
        elements.add("f62");
        elements.add("f184");
        elements.add("f66");
        elements.add("f14");
        elements.add("f69");
        elements.add("f72");
        elements.add("f75");
        elements.add("f78");
        elements.add("f81");
        elements.add("f84");
        elements.add("f87");
        elements.add("f37");
        elements.add("f55");
        elements.add("f188");
        elements.add("f186");
        elements.add("f183");
        elements.add("f100");
        elements.add("f41");
        elements.add("f49");
        elements.add("f57");

        Gson gson = new GsonBuilder().create();

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("http://push2.eastmoney.com/api/qt/clist/get?pn=1&pz=4000&po=0&np=1&fltt=2&invt=2&fid0=f4001&fid=f12&fs=m:0+t:6+f:!2,m:0+t:13+f:!2,m:0+t:80+f:!2,m:1+t:2+f:!2,m:1+t:23+f:!2,m:0+t:7+f:!2,m:1+t:3+f:!2&stat=1&fields="+ StringUtils.join(elements,",") +"&rt=52385340&_=1571560224479");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String text = EntityUtils.toString(entity);
            text = text.replaceAll("\"-\"", "0");
            StockResponse stockResponse = gson.fromJson(text, StockResponse.class);
            stockService.insertAll(stockResponse.getData().getDiff());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("success");
    }
}
