package io.philoyui.data.timer;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.data.entity.HourDataEntity;
import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.data.service.HourDataService;
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

/**
 * @author DELL
 */
@Component
public class HourTimer {

    @Autowired
    private PythonUtils pythonUtils;

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private HourDataService hourDataService;

    @Autowired
    private KLineDataDownloader kLineDataDownloader;

    @Scheduled(cron="0 29 10,11 * * 1-5")
    public void execute1(){
        handleHourTask();
    }

    @Scheduled(cron="0 55 13,14 * * 1-5")
    public void execute2(){
        handleHourTask();
    }

    private void handleHourTask() {

        //删除原来数据
        hourDataService.deleteAll();

        //下载最新数据
        kLineDataDownloader.download(TaskType.Min_15, new DownloadDataCallback() {
            @Override
            public void process(MyStockEntity stockEntity, KLineData[] kLineDataArray) {
                List<HourDataEntity> hourDataEntityList = new ArrayList<>();
                for (KLineData kLineData : kLineDataArray) {
                    HourDataEntity hourDataEntity = new HourDataEntity();
                    hourDataEntity.setSymbol(stockEntity.getSymbol());
                    hourDataEntity.setName(stockEntity.getStockName());
                    hourDataEntity.setDay(kLineData.getDay());
                    hourDataEntity.setDateString(DateFormatUtils.format(kLineData.getDay(), "yyyy-MM-dd HH:mm:ss"));
                    hourDataEntity.setOpen(Double.parseDouble(kLineData.getOpen()));
                    hourDataEntity.setHigh(Double.parseDouble(kLineData.getHigh()));
                    hourDataEntity.setLow(Double.parseDouble(kLineData.getLow()));
                    hourDataEntity.setClose(Double.parseDouble(kLineData.getClose()));
                    hourDataEntity.setVolume(Long.parseLong(kLineData.getVolume()));
                    hourDataEntity.setRecordTime(new Date());
                    hourDataEntityList.add(hourDataEntity);
                }
                hourDataService.insertAll(hourDataEntityList);
            }
        });

        List<MyStockEntity> stockEntityList = myStockService.list(SearchFilter.getDefault());
        for (MyStockEntity myStockEntity : stockEntityList) {
            pythonUtils.runPython("macd.py " + myStockEntity.getSymbol() + " hour");
        }

    }

}
