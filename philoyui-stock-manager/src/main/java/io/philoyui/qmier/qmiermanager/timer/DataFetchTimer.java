package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.Data30minService;
import io.philoyui.qmier.qmiermanager.service.DayDataService;
import io.philoyui.qmier.qmiermanager.service.MonthDataService;
import io.philoyui.qmier.qmiermanager.service.WeekDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DataFetchTimer {

    @Autowired
    private Data30minService data30minService;

    @Autowired
    private MonthDataService monthDataService;

    @Autowired
    private WeekDataService weekDataService;

    @Autowired
    private DayDataService dayDataService;

    @Scheduled(cron="0 0 23 * * 1-5")
    public void schedule30min(){
        data30minService.downloadHistory();
    }

    @Scheduled(cron="0 0 16 * * 1-5")
    public void scheduleDay(){
        dayDataService.downloadHistory();
    }

    @Scheduled(cron="0 30 3 ? * 7") //每周六
    public void scheduleWeek(){
        weekDataService.downloadHistory();
    }

    @Scheduled(cron="0 0 3 1 * ?") //每月1号3点
    public void scheduleMonth(){
        monthDataService.downloadHistory();
    }
}
