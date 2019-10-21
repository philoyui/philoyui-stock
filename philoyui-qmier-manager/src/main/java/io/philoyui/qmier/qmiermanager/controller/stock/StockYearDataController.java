package io.philoyui.qmier.qmiermanager.controller.stock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.east.StockResponse;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueqiuResponse;
import io.philoyui.qmier.qmiermanager.dao.stock.StockYearDataDao;
import io.philoyui.qmier.qmiermanager.service.stock.StockYearDataService;
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

/**
 *
 */
@RequestMapping
@Controller("/stock_year_data")
public class StockYearDataController {

    @Autowired
    private StockYearDataService stockYearDataService;

    @RequestMapping("/fetchXueqiu")
    public ResponseEntity<String> fetchXueqiu(){

        Gson gson = new GsonBuilder().create();

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://stock.xueqiu.com/v5/stock/finance/cn/indicator.json?symbol=SZ002075&type=Q4&is_detail=true&count=5");
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String text = EntityUtils.toString(entity);
            text = text.replaceAll("\"-\"", "0");
            XueqiuResponse xueqiuResponse = gson.fromJson(text, XueqiuResponse.class);

            System.out.println(gson.toJson(xueqiuResponse));



        } catch (IOException e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok("success");

    }


}
