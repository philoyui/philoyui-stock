package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.Data30minService;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.DataMonthService;
import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataFetchTimer {

    @Autowired
    private Data30minService data30minService;

    @Autowired
    private DataMonthService dataMonthService;

    @Autowired
    private DataWeekService dataWeekService;

    @Autowired
    private DataDayService dataDayService;

    @Scheduled(cron="0 0 23 * * 1-5") //下午6点
    public void schedule30min(){
        data30minService.downloadHistory();
    }

    @Scheduled(cron="0 0 3 1 * ?") //每月1号3点
    public void scheduleDay(){
        dataDayService.downloadHistory();
    }

    @Scheduled(cron="0 30 3 ? * 7") //每周六
    public void scheduleWeek(){
        dataWeekService.downloadHistory();
    }

    @Scheduled(cron="0 0 3 1 * ?") //每月1号3点
    public void scheduleMonth(){
        dataMonthService.downloadHistory();
    }
}
