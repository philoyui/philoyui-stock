package io.philoyui.qmier.qmiermanager.timer;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.Data30min;
import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.service.Data30minService;
import io.philoyui.qmier.qmiermanager.service.FinancialProductService;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data30minTimer {

    @Autowired
    private FinancialProductService financialProductService;

    @Autowired
    private Data30minService data30minService;

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    /**
     * 读取30min股票列表
     * @param args
     */
    public void main(String[] args){

        List<FinancialProductEntity> financialProductEntities = financialProductService.list(SearchFilter.getDefault());
        for (FinancialProductEntity financialProductEntity : financialProductEntities) {
            String fetchUrl = "http://money.finance.sina.com.cn/quotes_service/api/json_v2.php/CN_MarketData.getKLineData?symbol="+financialProductEntity.getSymbol()+"&scale=5&ma=5&datalen=100";
            try {
                Connection.Response response = Jsoup.connect(fetchUrl)
                        .header("Content-Type", "application/json")
                        .ignoreContentType(true)
                        .method(Connection.Method.GET)
                        .execute();

                HistoryData[] historyDataArray = gson.fromJson(response.body(), HistoryData[].class);

                List<Data30minEntity> data30minEntityList = new ArrayList<>();
                for (HistoryData historyData : historyDataArray) {
                    Data30minEntity data30minEntity = new Data30minEntity();
                    data30minEntity.setSymbol(financialProductEntity.getSymbol());
                    data30minEntity.setDay(historyData.getDay());
                    data30minEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    data30minEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                    data30minEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                    data30minEntity.setLow(Double.parseDouble(historyData.getLow()));
                    data30minEntity.setClose(Double.parseDouble(historyData.getClose()));
                    data30minEntity.setVolume(Long.parseLong(historyData.getVolume()));
                    data30minEntityList.add(data30minEntity);
                }
                data30minService.insertAll(data30minEntityList);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}
