package io.philoyui.qmier.qmiermanager.tradingview;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;

public class StockGetTest {

    @Test
    public void test_get_stock(){

        String fetchUrl = "https://gu.sina.cn/ft/api/jsonp.php/var%20_SI_15_1573205959334=/GlobalService.getMink?symbol=SI&type=15";
        Connection connect = Jsoup.connect(fetchUrl);
        connect.ignoreContentType(true);
        connect.header("Content-Type", "application/json");
        try {
            Document document = connect.get();
            System.out.println(document.text());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
