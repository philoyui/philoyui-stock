package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.DayTaskService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.day.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DayTaskServiceImpl implements DayTaskService {

    @Autowired
    private StockService stockService;

    @Autowired
    private MacdDayIndicatorProvider macdDayIndicatorProvider;

    @Autowired
    private CciIndicatorProvider cciIndicatorProvider;

    @Autowired
    private ThreeGoldenIndicatorProvider threeGoldenIndicatorProvider;

    @Autowired
    private RsiIndicatorProvider rsiIndicatorProvider;

    @Autowired
    private BigBuyIndicatorProvider bigBuyIndicatorProvider;

    @Autowired
    private TradingViewProvider tradingViewProvider;

    @Autowired
    private InvestorInfoIndicatorProvider investorInfoIndicatorProvider;

    public void execute(){

        for (StockEntity stockEntity : stockService.findAll()) {
            macdDayIndicatorProvider.processTags(stockEntity);
            cciIndicatorProvider.processTags(stockEntity);
            threeGoldenIndicatorProvider.processTags(stockEntity);
            rsiIndicatorProvider.processTags(stockEntity);
        }

        bigBuyIndicatorProvider.processGlobal();
        investorInfoIndicatorProvider.processGlobal();
        tradingViewProvider.processGlobal();
    }

    public void execute(StockEntity stockEntity){
        macdDayIndicatorProvider.processTags(stockEntity);
    }

}
