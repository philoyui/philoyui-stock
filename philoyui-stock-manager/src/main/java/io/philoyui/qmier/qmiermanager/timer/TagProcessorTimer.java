package io.philoyui.qmier.qmiermanager.timer;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.DataMonthService;
import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessor;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TagProcessorTimer {

    @Autowired
    private TagProcessorService tagProcessorService;

    @Autowired
    private DataDayService dataDayService;

    @Autowired
    private DataWeekService dataWeekService;

    @Autowired
    private DataMonthService dataMonthService;

    @Autowired
    private StockService stockService;


    /**
     *
     */
    @Scheduled(cron="* * 19 * * ?") //下午4点
    public void execute_day(){
        List<TagProcessor> tagProcessors = tagProcessorService.findAllDayTagProcessors();
        SearchFilter searchFilter = SearchFilter.getDefault();
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        for (StockEntity stockEntity : stockEntities) {
            ProcessorContext processorContext = new ProcessorContext();
            processorContext.setLowDataArray(dataDayService.findLowData(stockEntity));
            processorContext.setHighDataArray(dataDayService.findHighData(stockEntity));
            processorContext.setCloseDataArray(dataDayService.findCloseData(stockEntity));
            processorContext.setOpenDataArray(dataDayService.findOpenData(stockEntity));
            processorContext.setVolumeDataArray(dataDayService.findVolumeData(stockEntity));
            for (TagProcessor tagProcessor : tagProcessors) {
                tagProcessor.processEachStock(processorContext, stockEntity);
            }
        }
    }

    @Scheduled(cron="0 30 7 ? * 7") //每周六
    public void execute_week(){

        List<TagProcessor> tagProcessors = tagProcessorService.findAllWeekTagProcessors();

        SearchFilter searchFilter = SearchFilter.getDefault();
        List<StockEntity> stockEntities = stockService.list(searchFilter);

        for (StockEntity stockEntity : stockEntities) {
            ProcessorContext processorContext = new ProcessorContext();
            processorContext.setLowDataArray(dataWeekService.findLowData(stockEntity));
            processorContext.setHighDataArray(dataWeekService.findHighData(stockEntity));
            processorContext.setCloseDataArray(dataWeekService.findCloseData(stockEntity));
            processorContext.setOpenDataArray(dataWeekService.findOpenData(stockEntity));
            processorContext.setVolumeDataArray(dataWeekService.findVolumeData(stockEntity));

            for (TagProcessor tagProcessor : tagProcessors) {
                tagProcessor.processEachStock(processorContext, stockEntity);
            }

        }
    }

    @Scheduled(cron="0 0 8 1 * ?") //每月1号3点
    public void execute_month(){
        List<TagProcessor> tagProcessors = tagProcessorService.findAllMonthTagProcessors();

        SearchFilter searchFilter = SearchFilter.getDefault();
        List<StockEntity> stockEntities = stockService.list(searchFilter);

        for (StockEntity stockEntity : stockEntities) {
            ProcessorContext processorContext = new ProcessorContext();
            processorContext.setLowDataArray(dataMonthService.findLowData(stockEntity));
            processorContext.setHighDataArray(dataMonthService.findHighData(stockEntity));
            processorContext.setCloseDataArray(dataMonthService.findCloseData(stockEntity));
            processorContext.setOpenDataArray(dataMonthService.findOpenData(stockEntity));
            processorContext.setVolumeDataArray(dataMonthService.findVolumeData(stockEntity));
            for (TagProcessor tagProcessor : tagProcessors) {
                tagProcessor.processEachStock(processorContext, stockEntity);
            }

        }
    }

}
