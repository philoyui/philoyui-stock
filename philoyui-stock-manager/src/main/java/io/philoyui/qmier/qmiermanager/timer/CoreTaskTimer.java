package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.StockIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CoreTaskTimer {

    @Autowired
    private StockIndicatorService stockIndicatorService;

    @Autowired
    private MyStockService myStockService;

    /**
     * 1. 遍历所有的股票，清理历史数据，下载最新的数据，入数据库
     * 2. 遍历所有的策略，调用各自策略的python脚本，清理之前的标签数据，解析指标数据到数据库中
     * 3. 使用java代码，解析数据库中的指标数据，清理之前的标签数据，新数据入库，记录日志
     * 4. 判断是否加入自选
     */
    @Scheduled(cron="0 0 16 * * 1-5")
    public void executeDayTask(){
        stockIndicatorService.executeDayTask();
    }

    @Scheduled(cron="0 30 3 ? * 7") //每周六
    public void executeWeekTask(){
        stockIndicatorService.executeWeekTask();
    }

    @Scheduled(cron="0 0 3 1 * ?") //每月1号3点
    public void executeMonthTask(){
        stockIndicatorService.executeMonthTask();
    }
}
