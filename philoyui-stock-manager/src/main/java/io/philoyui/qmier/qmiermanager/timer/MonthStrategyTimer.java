package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MonthStrategyTimer {

    @Autowired
    private StockStrategyService stockStrategyService;

    @Scheduled(cron="0 0 8 1 * ?") //每月1号3点
    public void fetcher(){

        stockStrategyService.processWithMonthTimer();

    }

}
