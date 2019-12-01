package io.philoyui.qmier.qmiermanager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
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

            HistoryData[] historyDataArray = gson.fromJson(response.body(), HistoryData[].class);

        List<DataDayEntity> dataDayEntityList = new ArrayList<>();
        for (HistoryData historyData : historyDataArray) {
            DataDayEntity DataDayEntity = new DataDayEntity();
            DataDayEntity.setSymbol("sh000001");
            DataDayEntity.setDay(historyData.getDay());
            DataDayEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
            DataDayEntity.setOpen(Double.parseDouble(historyData.getOpen()));
            DataDayEntity.setHigh(Double.parseDouble(historyData.getHigh()));
            DataDayEntity.setLow(Double.parseDouble(historyData.getLow()));
            DataDayEntity.setClose(Double.parseDouble(historyData.getClose()));
            DataDayEntity.setVolume(Long.parseLong(historyData.getVolume()));
            dataDayEntityList.add(DataDayEntity);
        }
        System.out.println(gson.toJson(dataDayEntityList));

    }

}
