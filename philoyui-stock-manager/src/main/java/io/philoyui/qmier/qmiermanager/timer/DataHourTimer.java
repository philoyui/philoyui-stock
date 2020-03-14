package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.DataHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataHourTimer implements TimeScheduler{

    @Autowired
    private DataHourService dataHourService;

    /**
     * 读取30min股票列表
     * @param args
     */
    @Scheduled(cron="0 0 15 * * 1-5") //凌晨1点
    @Override
    public void schedule(){
        dataHourService.downloadHistory();
    }

}
