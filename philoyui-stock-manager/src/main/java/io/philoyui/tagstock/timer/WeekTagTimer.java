package io.philoyui.tagstock.timer;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.indicator.week.KdjWeekIndicatorProvider;
import io.philoyui.tagstock.indicator.week.MacdWeekIndicatorProvider;
import io.philoyui.tagstock.indicator.week.RsiWeekIndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class WeekTagTimer {

    @Autowired
    private StockService stockService;

    @Value("${application.python.path}")
    private String pythonPath;

    @Autowired
    private KdjWeekIndicatorProvider kdjWeekIndicatorProvider;

    @Autowired
    private MacdWeekIndicatorProvider macdWeekIndicatorProvider;

    @Autowired
    private RsiWeekIndicatorProvider rsiWeekIndicatorProvider;

    @Autowired
    private TagStockService tagStockService;

    @Scheduled(cron="0 30 3 ? * 7") //每周六
    public void execute() {

        tagStockService.cleanOld(IntervalType.Week);

        runPython("week_task.py");
        for (StockEntity stockEntity : stockService.findAll()) {
            if(!stockEntity.getSymbol().startsWith("*ST")||!stockEntity.getSymbol().startsWith("ST")) {
                kdjWeekIndicatorProvider.processTags(stockEntity);
                macdWeekIndicatorProvider.processTags(stockEntity);
                rsiWeekIndicatorProvider.processTags(stockEntity);
            }
        }
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
