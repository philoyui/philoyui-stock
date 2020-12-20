package io.philoyui.tagstock.timer;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.indicator.day.*;
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
    private StockService stockService;

    @Value("${application.python.path}")
    private String pythonPath;

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

        runPython("day_task.py");
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

    private void runPython(String pythonName) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(pythonPath + pythonName);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
