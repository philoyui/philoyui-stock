package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.TradingViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TradingViewTimer {

    @Autowired
    private TradingViewService tradingViewService;

    @Scheduled(cron="0 45 16 * * 1-5")
    public void execute(){
        tradingViewService.fetchCurrent();
    }

}