package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.DayDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CloseDataTimer implements TimeScheduler{

    @Autowired
    private DayDataService dayDataService;

    @Scheduled(cron="0 40 14 * * 1-5")
    @Override
    public void schedule(){
        dayDataService.processEstimateDayData();
    }

}
