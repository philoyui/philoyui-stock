package io.philoyui.tagstock.timer;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.indicator.day.*;
import io.philoyui.utils.PythonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class DayTagTimer {

    @Autowired
    private PythonUtils pythonUtils;

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
    private TagStockService tagStockService;

    @Autowired
    private InvestorInfoIndicatorProvider investorInfoIndicatorProvider;

    @Scheduled(cron="0 30 18 * * 1-5")
    public void execute(){

        tagStockService.cleanOld(IntervalType.Day);

        pythonUtils.runPython("day_task.py");
        for (StockEntity stockEntity : stockService.findAll()) {
            macdDayIndicatorProvider.processTags(stockEntity);
            cciIndicatorProvider.processTags(stockEntity);
            rsiIndicatorProvider.processTags(stockEntity);
        }

        threeGoldenIndicatorProvider.processGlobal();
        bigBuyIndicatorProvider.processGlobal();
        investorInfoIndicatorProvider.processGlobal();
        tradingViewProvider.processGlobal();
    }



}
