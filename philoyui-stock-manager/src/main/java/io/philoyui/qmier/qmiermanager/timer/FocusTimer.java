package io.philoyui.qmier.qmiermanager.timer;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.common.base.Strings;
import io.philoyui.qmier.qmiermanager.entity.FocusStockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockIndicatorEntity;
import io.philoyui.qmier.qmiermanager.service.Data30minService;
import io.philoyui.qmier.qmiermanager.service.FocusStockService;
import io.philoyui.qmier.qmiermanager.service.StockIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class FocusTimer {

    private FocusStockService focusStockService;

    private Data30minService data30minService;

    @Autowired
    private StockIndicatorService stockIndicatorService;

    @Value("${application.python.path}")
    private String pythonPath;

    public void exec(){

        List<FocusStockEntity> focusStockEntities = focusStockService.list(SearchFilter.getDefault());

        for (FocusStockEntity focusStockEntity : focusStockEntities) {
            data30minService.downloadHistory(focusStockEntity);
            List<StockIndicatorEntity> min30StockIndicators  = stockIndicatorService.findMin30Enable();
            for (StockIndicatorEntity min30StockIndicator : min30StockIndicators) {
                if(!Strings.isNullOrEmpty(min30StockIndicator.getPythonName())){
                    parseIndicatorDataUsePython(min30StockIndicator.getPythonName(),focusStockEntity.getSymbol(),"30Min");
                }


            }
        }
    }

    private void parseIndicatorDataUsePython(String pythonName, String symbol, String interval) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(pythonPath + pythonName + " " + symbol + " " + interval);// 执行py文件
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
