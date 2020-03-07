package io.philoyui.qmier.qmiermanager;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialMarketEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.to.KLineData;
import io.philoyui.qmier.qmiermanager.to.ProductData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SinaStockGetTest {

    @Test
    public void test_stock_get() throws IOException {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        String fetchUrl = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol=sh000001&scale=960&ma=no&datalen=100";
            Connection.Response response = Jsoup.connect(fetchUrl)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();

            KLineData[] KLineDataArray = gson.fromJson(response.body(), KLineData[].class);

        List<DataDayEntity> dataDayEntityList = new ArrayList<>();
        for (KLineData KLineData : KLineDataArray) {
            DataDayEntity DataDayEntity = new DataDayEntity();
            DataDayEntity.setSymbol("sh000001");
            DataDayEntity.setDay(KLineData.getDay());
            DataDayEntity.setDateString(DateFormatUtils.format(KLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
            DataDayEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
            DataDayEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
            DataDayEntity.setLow(Double.parseDouble(KLineData.getLow()));
            DataDayEntity.setClose(Double.parseDouble(KLineData.getClose()));
            DataDayEntity.setVolume(Long.parseLong(KLineData.getVolume()));
            dataDayEntityList.add(DataDayEntity);
        }
        System.out.println(gson.toJson(dataDayEntityList));

    }


    @Test
    public void test_stocks_get() throws IOException {

        Gson gson = new GsonBuilder().create();

        String fetchUrl = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData?page=1&num=360&sort=symbol&asc=1&node=sh_a&symbol=&_s_r_a=page";
        try {
            Connection.Response response = Jsoup.connect(fetchUrl)
                    .header("Content-Type", "application/json")
                    .ignoreContentType(true)
                    .method(Connection.Method.GET)
                    .execute();
            String result = response.body().replaceAll("symbol","\"symbol\"")
                    .replaceAll("code","\"code\"")
                    .replaceAll("name","\"name\"")
                    .replaceAll("trade","\"trade\"")
                    .replaceAll("pricechange","\"pricechange\"")
                    .replaceAll("changepercent","\"changepercent\"")
                    .replaceAll("sell","\"sell\"")
                    .replaceAll("settlement","\"settlement\"")
                    .replaceAll("open","\"open\"")
                    .replaceAll("high","\"high\"")
                    .replaceAll("low","\"low\"")
                    .replaceAll("volume","\"volume\"")
                    .replaceAll("amount","\"amount\"")
                    .replaceAll("ticktime","\"ticktime\"")
                    .replaceAll("per:","\"per\":")
                    .replaceAll("pb","\"pb\"")
                    .replaceAll("mktcap","\"mktcap\"")
                    .replaceAll("nmc","\"nmc\"")
                    .replaceAll("buy","\"buy\"")
                    .replaceAll("turnoverratio","\"turnoverratio\"")
                    ;

            ProductData[] productDataArray = gson.fromJson(result, ProductData[].class);
            if(productDataArray==null){
                return;
            }

            List<StockEntity> stockEntities = Lists.newArrayList();

            for (ProductData productData : productDataArray) {
                StockEntity stockEntity = new StockEntity();
                stockEntity.setSymbol(productData.getSymbol());
                stockEntity.setCode(productData.getCode());
                stockEntity.setName(productData.getName());
                stockEntity.setModifyTime(new Date());
                stockEntity.setPb(productData.getPb());
                stockEntity.setPer(productData.getPer());
                stockEntity.setTurnoverRatio(productData.getTurnOverRatio());
                stockEntity.setTotalPrice(productData.getMktCap());
                stockEntities.add(stockEntity);
            }

            System.out.println(stockEntities.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
