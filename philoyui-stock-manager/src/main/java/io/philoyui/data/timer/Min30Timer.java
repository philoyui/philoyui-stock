package io.philoyui.data.timer;

import io.philoyui.data.entity.Min30DataEntity;
import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.data.service.Min30DataService;
import io.philoyui.stock.entity.enu.TaskType;
import io.philoyui.stock.service.DownloadDataCallback;
import io.philoyui.stock.service.KLineDataDownloader;
import io.philoyui.stock.to.KLineData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Min30Timer {

    @Autowired
    private Min30DataService min30DataService;

    @Autowired
    private KLineDataDownloader kLineDataDownloader;


    @Scheduled(cron="0 30,0 9,10,11,13,14 * * 1-5")
    public void execute(){

        min30DataService.deleteAll();

        kLineDataDownloader.download(TaskType.Min_15, new DownloadDataCallback() {
            @Override
            public void process(MyStockEntity stockEntity, KLineData[] kLineDataArray) {
                List<Min30DataEntity> Min30DataEntityList = new ArrayList<>();
                for (KLineData kLineData : kLineDataArray) {
                    Min30DataEntity Min30DataEntity = new Min30DataEntity();
                    Min30DataEntity.setSymbol(stockEntity.getSymbol());
                    Min30DataEntity.setName(stockEntity.getStockName());
                    Min30DataEntity.setDay(kLineData.getDay());
                    Min30DataEntity.setDateString(DateFormatUtils.format(kLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    Min30DataEntity.setOpen(Double.parseDouble(kLineData.getOpen()));
                    Min30DataEntity.setHigh(Double.parseDouble(kLineData.getHigh()));
                    Min30DataEntity.setLow(Double.parseDouble(kLineData.getLow()));
                    Min30DataEntity.setClose(Double.parseDouble(kLineData.getClose()));
                    Min30DataEntity.setVolume(Long.parseLong(kLineData.getVolume()));
                    Min30DataEntity.setRecordTime(new Date());
                    Min30DataEntityList.add(Min30DataEntity);
                }
                min30DataService.insertAll(Min30DataEntityList);
            }
        });

    }

}
