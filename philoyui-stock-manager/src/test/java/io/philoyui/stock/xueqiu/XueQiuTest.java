package io.philoyui.stock.xueqiu;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;

public class XueQiuTest {

    @Test
    public void test_fetch(){
        Connection.Response response = null;
        try {
            response = Jsoup.connect("https://stock.xueqiu.com/v5/stock/finance/cn/indicator.json?symbol=sh600000&is_detail=true&count=1000")
                    .header("Content-Type", "application/json")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36 Aoyou/PXczfGBSJFBcOl5bNTtBfSOuB-zziZGMMchqU3jBuKajiO-Odnp_HyLN")
                    .cookie("xq_r_token", "a4d08b15013b25dbd48edc595136989c7c471aa4")
                    .cookie("xq_a_token", "6bcf5383a71f0c5bdd54bae291e2ba227f78c04e")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .method(Connection.Method.GET)
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
    }

}
