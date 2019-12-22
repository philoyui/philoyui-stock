package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.DataDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataDayTimer implements TimeScheduler{

    @Autowired
    private DataDayService dataDayService;

    /**
     * 读取30min股票列表
     * @param args
     */
    @Scheduled(cron="* * 16 * * ?") //每晚9点
    @Override
    public void schedule(){
        dataDayService.downloadHistory();
    }

}
