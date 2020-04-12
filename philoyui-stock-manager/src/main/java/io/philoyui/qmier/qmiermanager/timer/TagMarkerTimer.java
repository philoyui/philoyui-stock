package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TagMarkerTimer {

    @Autowired
    private StockStrategyService stockStrategyService;

    /**
     *
     */
    @Scheduled(cron="* * 19 * * ?") //下午4点
    public void execute_day(){
        stockStrategyService.processWithDayTimer();
    }

    @Scheduled(cron="0 30 7 ? * 7") //每周六
    public void execute_week(){
        stockStrategyService.processWithWeekTimer();
    }

    @Scheduled(cron="0 0 8 1 * ?") //每月1号3点
    public void execute_month(){
        stockStrategyService.processWithMonthTimer();
    }

}
