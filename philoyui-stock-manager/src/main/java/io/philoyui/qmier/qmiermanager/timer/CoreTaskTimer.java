package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.service.*;
import io.philoyui.qmier.qmiermanager.service.indicator.StockIndicator;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CoreTaskTimer {

    @Autowired
    private StockService stockService;

    @Autowired
    private DayDataService dayDataService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private StockStrategyService stockStrategyService;

    @Autowired
    private IndicatorDataService indicatorDataService;

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private TaskLogService taskLogService;

    /**
     * 1. 遍历所有的股票，清理历史数据，下载最新的数据，入数据库
     * 2. 遍历所有的策略，调用各自策略的python脚本，清理之前的标签数据，解析指标数据到数据库中
     * 3. 使用java代码，解析数据库中的指标数据，清理之前的标签数据，新数据入库，记录日志
     * 4. 判断是否加入自选
     */
    public void executeDayTask(){

        List<StockEntity> stockEntities = stockService.findAll();

        for (StockEntity stockEntity : stockEntities) {

            dayDataService.downloadHistory(stockEntity);

            List<StockIndicator> stockIndicators  = indicatorService.findAllEnable();

            List<TagEntity> tagEntities= new ArrayList<>();

            for (StockIndicator stockIndicator : stockIndicators) {

                parseIndicatorDataUsePython(stockEntity, stockIndicator);

                tagEntities.addAll(stockIndicator.parseAndRecordTags(stockEntity,stockIndicator));

            }

            myStockService.judgeAndRecord(stockEntity,tagEntities);

            taskLogService.logDownloadSuccess(stockEntity,tagEntities);

        }

    }

    /**
     * 清理历史指标数据
     * @param stockEntity
     * @param stockIndicator
     */
    private void cleanIndicatorData(StockEntity stockEntity, StockIndicator stockIndicator) {

    }

    private void parseIndicatorDataUsePython(StockEntity stockEntity, StockIndicator stockIndicator) {

        cleanIndicatorData(stockEntity,stockIndicator);

        Process process;
        try {
            process = Runtime.getRuntime().exec("python /root/python/" + stockIndicator.getPythonName() + " " + stockEntity.getSymbol());// 执行py文件
            //用输入输出流来截取结果
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
