package io.philoyui.qmier.qmiermanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.east.StockResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EastStockTest {

    public static void main(String[] args){

        List<String> elements = new ArrayList<>();

        for (int i = 1; i < 1000; i++) {
            elements.add("f"+i);
        }

        Gson gson = new GsonBuilder().create();

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String fetchUrl = "http://push2.eastmoney.com/api/qt/clist/get?pn=1&pz=10&po=0&np=1&fltt=2&invt=2&fid0=f4001&fid=f12&fs=m:0+t:6+f:!2,m:0+t:13+f:!2,m:0+t:80+f:!2,m:1+t:2+f:!2,m:1+t:23+f:!2,m:0+t:7+f:!2,m:1+t:3+f:!2&stat=1&fields=" + StringUtils.join(elements, ",") + "&rt=52385340&_=1571560224479";
            System.out.println(fetchUrl);
            HttpGet httpGet = new HttpGet(fetchUrl);
            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String text = EntityUtils.toString(entity);
            text = text.replaceAll("\"-\"", "0");
            StockResponse stockResponse = gson.fromJson(text, StockResponse.class);
            System.out.println(stockResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
