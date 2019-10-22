package io.philoyui.qmier.qmiermanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClient;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClientImpl;
import io.philoyui.qmier.qmiermanager.client.xueqiu.request.AnnualReportRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.AnnualReportResponse;

public class XueQiuYearTest {

    public static void main(String[] args) {

        XueQiuClient xueQiuClient = new XueQiuClientImpl();

        AnnualReportRequest reportRequest = new AnnualReportRequest();

        AnnualReportResponse response = xueQiuClient.execute(reportRequest);

        Gson gson = new GsonBuilder().create();

//        try {

//            Connection.Response response = Jsoup.connect("https://stock.xueqiu.com/v5/stock/finance/cn/indicator.json?symbol=SZ002075&type=Q4&is_detail=true&count=5")
//                    .header("Content-Type", "application/json")
//                    .cookie("xq_r_token", "5d9da1b897618a378ec339215420c06ac7674a3a")
//                    .cookie("xq_a_token", "63de0d2b2c9432d0675782fcd02ec6182112bffd")
//                    .ignoreContentType(true)
//                    .method(Connection.Method.GET)
//                    .execute();
//
//            XueQiuBaseResponse xueqiuBaseResponse = gson.fromJson(response.body(), XueQiuBaseResponse.class);

//            System.out.println(gson.toJson(xueqiuBaseResponse));

//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
