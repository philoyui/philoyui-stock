package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StrategyTimer {

    @Autowired
    private StockStrategyService stockStrategyService;

    @Scheduled(cron="0 0 8 1 * ?") //每月1号3点
    public void fetcherMonth(){
        stockStrategyService.processWithMonthTimer();
    }

    @Scheduled(cron="0 30 7 ? * 7") //每周六
    public void fetcherWeek(){
        stockStrategyService.processWithWeekTimer();
    }

    @Scheduled(cron="* * 20 * * ?") //下午4点
    public void fetcher(){
        stockStrategyService.processWithDayTimer();
    }
}
