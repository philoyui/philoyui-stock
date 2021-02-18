package io.philoyui.tagstock.timer;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.indicator.minute.Macd15IndicatorProvider;
import io.philoyui.tagstock.indicator.minute.Macd30IndicatorProvider;
import io.philoyui.tagstock.indicator.minute.Macd60IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class MinTagTimer {

    @Autowired
    private StockService stockService;

    @Value("${application.python.path}")
    private String pythonPath;

    @Autowired
    private Macd30IndicatorProvider macd30IndicatorProvider;

    @Autowired
    private Macd15IndicatorProvider macd15IndicatorProvider;

    @Autowired
    private Macd60IndicatorProvider macd60IndicatorProvider;

    @Autowired
    private TagStockService tagStockService;

    @Scheduled(cron="0 0 21 * * 1-5")
    public void execute() {

        tagStockService.cleanOld(IntervalType.Min15);
        tagStockService.cleanOld(IntervalType.Min30);
        tagStockService.cleanOld(IntervalType.Min60);

        runPython("min_60_task.py");
        for (StockEntity stockEntity : stockService.findAll()) {
            if(!stockEntity.getSymbol().startsWith("*ST")||!stockEntity.getSymbol().startsWith("ST")) {
                macd60IndicatorProvider.processTags(stockEntity);
            }
        }

        runPython("min_30_task.py");
        for (StockEntity stockEntity : stockService.findAll()) {
            if(!stockEntity.getSymbol().startsWith("*ST")||!stockEntity.getSymbol().startsWith("ST")) {
                macd30IndicatorProvider.processTags(stockEntity);
            }
        }

        runPython("min_15_task.py");

        for (StockEntity stockEntity : stockService.findAll()) {
            if(!stockEntity.getSymbol().startsWith("*ST")||!stockEntity.getSymbol().startsWith("ST")) {
                macd15IndicatorProvider.processTags(stockEntity);
            }
        }
    }


    private void runPython(String pythonName) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(pythonPath + pythonName);
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
