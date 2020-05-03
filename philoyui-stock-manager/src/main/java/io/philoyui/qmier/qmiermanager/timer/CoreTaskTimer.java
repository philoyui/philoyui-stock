package io.philoyui.qmier.qmiermanager.timer;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockIndicatorEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.*;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CoreTaskTimer {



    @Autowired
    private StockIndicatorService stockIndicatorService;

    @Autowired
    private StockStrategyService stockStrategyService;

    @Autowired
    private MyStockService myStockService;





    /**
     * 1. 遍历所有的股票，清理历史数据，下载最新的数据，入数据库
     * 2. 遍历所有的策略，调用各自策略的python脚本，清理之前的标签数据，解析指标数据到数据库中
     * 3. 使用java代码，解析数据库中的指标数据，清理之前的标签数据，新数据入库，记录日志
     * 4. 判断是否加入自选
     */
    public void executeDayTask(){
        stockIndicatorService.executeDayTask();
    }


    public void executeWeekTask(){

    }


}
