package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.field.DoubleFieldDefinition;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.service.FinancialProductService;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class DataDownloader {

    private static final Logger LOG = LoggerFactory.getLogger(DataDownloader.class);

    @Autowired
    private FinancialProductService financialProductService;

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    public void process(DataType dataType, DataDownloadInterface dataDownloadInterface) {
        List<FinancialProductEntity> financialProductEntities = financialProductService.findEnable();
        for (FinancialProductEntity financialProductEntity : financialProductEntities) {
            String fetchUrl = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+financialProductEntity.getSymbol()+"&scale="+dataType.getMinute()+"&ma=no&datalen=100";
            try {
                Connection.Response response = Jsoup.connect(fetchUrl)
                        .header("Content-Type", "application/json")
                        .ignoreContentType(true)
                        .method(Connection.Method.GET)
                        .execute();

                HistoryData[] historyDataArray = gson.fromJson(response.body(), HistoryData[].class);

                dataDownloadInterface.process(historyDataArray,financialProductEntity);

                LOG.info("下载历史数据成功：" + dataType + " " + financialProductEntity.getSymbol());

            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
