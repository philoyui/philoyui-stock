package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.DataDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CloseTimer implements TimeScheduler{

    @Autowired
    private DataDayService dataDayService;

    @Scheduled(cron="0 40 14 * * 1-5")
    @Override
    public void schedule(){
        dataDayService.processEstimateDayData();
    }

}
