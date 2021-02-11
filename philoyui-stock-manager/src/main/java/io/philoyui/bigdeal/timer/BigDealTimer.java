package io.philoyui.bigdeal.timer;

import io.philoyui.bigdeal.service.BigDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BigDealTimer {

    @Autowired
    private BigDealService bigDealService;

    public void execute(){

        bigDealService.fetchLastData();

    }

}
