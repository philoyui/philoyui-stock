package io.philoyui.stock.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.TaskType;
import io.philoyui.stock.service.DownloadDataCallback;
import io.philoyui.stock.service.KLineDataDownloader;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.to.KLineData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class KLineDataDownloaderImpl implements KLineDataDownloader {

    private static final Logger LOG = LoggerFactory.getLogger(KLineDataDownloaderImpl.class);

    @Autowired
    private StockService stockService;

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    @Override
    public void download(TaskType taskType, DownloadDataCallback downloadDataCallback) {
        List<StockEntity> financialProductEntities = stockService.findEnable();
        for (StockEntity financialProduct : financialProductEntities) {
            String fetchUrl = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+financialProduct.getSymbol()+"&scale="+ taskType.getMinute()+"&ma=no&datalen=80";
            try {
                Connection.Response response = Jsoup.connect(fetchUrl)
                        .header("Content-Type", "application/json")
                        .ignoreContentType(true)
                        .method(Connection.Method.GET)
                        .execute();

                KLineData[] KLineDataArray = gson.fromJson(response.body(), KLineData[].class);

                downloadDataCallback.process(financialProduct, KLineDataArray);

                LOG.info("下载历史数据成功：" + taskType + " " + financialProduct.getSymbol());

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
}
