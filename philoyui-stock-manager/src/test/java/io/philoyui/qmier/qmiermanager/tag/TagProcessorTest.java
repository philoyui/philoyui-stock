package io.philoyui.qmier.qmiermanager.tag;


import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.processor.BigBuyTagProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TagProcessorTest {

    @Autowired
    private DataDayService dataDayService;

    @Autowired
    private StockService stockService;


    /**
     * 1. 定时执行标签任务，执行全局性任务，再执行扫描性任务
     */
    @Test
    public void test_(){

        BigBuyTagProcessor tagProcessor = new BigBuyTagProcessor();

        SearchFilter searchFilter = SearchFilter.getDefault();
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        for (StockEntity stockEntity : stockEntities) {
            ProcessorContext processorContext = new ProcessorContext();
            processorContext.setLowDataArray(dataDayService.findLowData(stockEntity));
            processorContext.setHighDataArray(dataDayService.findHighData(stockEntity));
            processorContext.setCloseDataArray(dataDayService.findCloseData(stockEntity));
            processorContext.setOpenDataArray(dataDayService.findOpenData(stockEntity));
            processorContext.setVolumeDataArray(dataDayService.findVolumeData(stockEntity));
            tagProcessor.processEachStock(processorContext, stockEntity);
        }

    }

    /**
     * 2. 手动执行单一的标签，通过标签获取TagProcessor，在执行TagProcessor
     */


    /**
     * 3. 选择股票(趋势上行) -> 排除，写入自选
     */

    /**
     * 4. 查看标签下的所有股票
     */


}
