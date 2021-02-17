package io.philoyui.data.timer;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.data.entity.Min15DataEntity;
import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.data.service.Min15DataService;
import io.philoyui.mystock.service.MyStockService;
import io.philoyui.stock.entity.enu.TaskType;
import io.philoyui.stock.service.DownloadDataCallback;
import io.philoyui.stock.service.KLineDataDownloader;
import io.philoyui.stock.to.KLineData;
import io.philoyui.utils.PythonUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Min15Timer {

    @Autowired
    private Min15DataService min15DataService;

    @Autowired
    private PythonUtils pythonUtils;

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private KLineDataDownloader kLineDataDownloader;

    @Scheduled(cron="0 0,15,30,45 9,10,11,13,14 * * 1-5")
    public void execute(){

        min15DataService.deleteAll();

        kLineDataDownloader.download(TaskType.Min_15, new DownloadDataCallback() {
            @Override
            public void process(MyStockEntity stockEntity, KLineData[] kLineDataArray) {
                List<Min15DataEntity> min15DataEntityList = new ArrayList<>();
                for (KLineData kLineData : kLineDataArray) {
                    Min15DataEntity min15DataEntity = new Min15DataEntity();
                    min15DataEntity.setSymbol(stockEntity.getSymbol());
                    min15DataEntity.setName(stockEntity.getStockName());
                    min15DataEntity.setDay(kLineData.getDay());
                    min15DataEntity.setDateString(DateFormatUtils.format(kLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    min15DataEntity.setOpen(Double.parseDouble(kLineData.getOpen()));
                    min15DataEntity.setHigh(Double.parseDouble(kLineData.getHigh()));
                    min15DataEntity.setLow(Double.parseDouble(kLineData.getLow()));
                    min15DataEntity.setClose(Double.parseDouble(kLineData.getClose()));
                    min15DataEntity.setVolume(Long.parseLong(kLineData.getVolume()));
                    min15DataEntity.setRecordTime(new Date());
                    min15DataEntityList.add(min15DataEntity);
                }
                min15DataService.insertAll(min15DataEntityList);
            }
        });

        List<MyStockEntity> stockEntityList = myStockService.list(SearchFilter.getDefault());
        for (MyStockEntity myStockEntity : stockEntityList) {
            pythonUtils.runPython("macd.py " + myStockEntity.getSymbol() + " min15");
        }
    }

}
