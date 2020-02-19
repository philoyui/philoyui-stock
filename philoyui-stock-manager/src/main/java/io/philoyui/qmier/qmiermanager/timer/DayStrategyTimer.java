package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Component
public class DayStrategyTimer {

    @Autowired
    private StockStrategyService stockStrategyService;

    @Scheduled(cron="* * 20 * * ?") //下午4点
    public void fetcher(){

        stockStrategyService.processWithDayTimer();

    }

}
