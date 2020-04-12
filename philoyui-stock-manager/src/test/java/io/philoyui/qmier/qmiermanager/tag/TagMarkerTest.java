package io.philoyui.qmier.qmiermanager.tag;


import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.service.*;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.TagMarkerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TagMarkerTest {

    @Autowired
    private DayDataService dayDataService;

    @Autowired
    private WeekDataService weekDataService;

    @Autowired
    private MonthDataService monthDataService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockStrategyService stockStrategyService;

    @Autowired
    private TagMarkerService tagMarkerService;

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private TagStockService tagStockService;

    /**
     *
     * 定时器分别打标，日线标签，周线标签，月线标签
     *
     * 标签分为：1. 日线标签 2. 周线标签 3. 月线标签
     * 股票打标处理器有两种：1. 全局打标处理器  2. 个股打标处理器
     *
     * 1. 全局性打标处理器
     * 2. 遍历所有股票的历史数据，并调用个股打标处理器
     */
    @Test
    public void test_day(){
        stockStrategyService.processWithDayTimer();
    }

    @Test
    public void test_week(){
        stockStrategyService.processWithWeekTimer();
    }

    @Test
    public void test_month(){

        stockStrategyService.processWithMonthTimer();

    }

    /**
     * 2. 手动执行单一的标签，通过标签获取TagProcessor，在执行TagProcessor
     */
    public void test_execute_single(){


    }



    /**
     * 3. 选择股票(趋势上行) -> 排除，写入自选
     */

    public void test_select(){

        myStockService.obtainEveryDay();

    }

    /**
     * 4. 查看标签下的所有股票
     */


}
