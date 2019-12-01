package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.DataHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataHourTimer {

    @Autowired
    private DataHourService dataHourService;

    /**
     * 读取30min股票列表
     * @param args
     */
    @Scheduled(cron="* * 1 * * ? ") //凌晨1点
    public void fetcher(){
        dataHourService.downloadHistory();
    }

}
