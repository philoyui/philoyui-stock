package io.philoyui.qmier.qmiermanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

public class XueQiuStockTest {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        try {
            Connection.Response response = Jsoup.connect("https://xueqiu.com/service/v5/stock/screener/quote/list?page=1&size=90&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz&_=1571724324541")
                    .header("Content-Type", "application/json")
                    .cookie("xq_r_token", "5d9da1b897618a378ec339215420c06ac7674a3a")
                    .cookie("xq_a_token", "63de0d2b2c9432d0675782fcd02ec6182112bffd")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
            System.out.println(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
