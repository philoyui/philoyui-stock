package io.philoyui.qmier.qmiermanager.tagstock.timer;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.day.KdjDayIndicatorProvider;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.week.KdjWeekIndicatorProvider;
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

    @Scheduled(cron="0 30 3 ? * 7") //每周六
    public void execute() {
        runPython("week_task.py");
        for (StockEntity stockEntity : stockService.findAll()) {
            kdjWeekIndicatorProvider.processTags(stockEntity);
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
