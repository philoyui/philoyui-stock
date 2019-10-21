package io.philoyui.qmier.qmiermanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueqiuResponse;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class XueqiuTest {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().create();

        try {

            Connection.Response response = Jsoup.connect("https://stock.xueqiu.com/v5/stock/finance/cn/indicator.json?symbol=SZ002075&type=Q4&is_detail=true&count=5")
                    .header("Content-Type", "application/json")
                    .cookie("xq_r_token", "5d9da1b897618a378ec339215420c06ac7674a3a")
                    .cookie("xq_a_token", "63de0d2b2c9432d0675782fcd02ec6182112bffd")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();

            XueqiuResponse xueqiuResponse = gson.fromJson(response.body(),XueqiuResponse.class);

            System.out.println(gson.toJson(xueqiuResponse));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
