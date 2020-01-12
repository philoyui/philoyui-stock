package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataWeekTimer implements TimeScheduler{

    @Autowired
    private DataWeekService dataWeekService;

    /**
     * 读取30min股票列表
     * @param args
     */
    @Scheduled(cron="0 30 3 ? * 1") //每周日
    @Override
    public void schedule(){
        dataWeekService.downloadHistory();
    }

}
