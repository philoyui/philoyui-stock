package io.philoyui.qmier.qmiermanager.tagstock.timer;

import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.month.DishStockIndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class MonthTagTimer {

    @Autowired
    private StockService stockService;

    @Value("${application.python.path}")
    private String pythonPath;

    @Autowired
    private DishStockIndicatorProvider dishStockIndicatorProvider;

//    @Scheduled(cron="0 0 3 1 * ?") //每月1号3点
    public void execute() {
        runPython("min_60_task.py");
        dishStockIndicatorProvider.processGlobal();
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
