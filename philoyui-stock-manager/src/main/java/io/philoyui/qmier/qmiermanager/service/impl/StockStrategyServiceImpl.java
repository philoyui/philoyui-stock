package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.StockStrategyDao;
import io.philoyui.qmier.qmiermanager.domain.StockHistoryData;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.*;
import io.philoyui.qmier.qmiermanager.service.tag.TagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.TagMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StockStrategyServiceImpl extends GenericServiceImpl<StockStrategyEntity,Long> implements StockStrategyService {

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

    @Autowired
    private StockStrategyDao stockStrategyDao;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

    @Override
    protected GenericDao<StockStrategyEntity, Long> getDao() {
        return stockStrategyDao;
    }

    @Override
    public void tagStock(StockStrategyEntity stockStrategyEntity) {
        tagStock(stockStrategyEntity.getIdentifier());
        stockStrategyEntity.setLastExecuteTime(new Date());
        this.update(stockStrategyEntity);
    }

    @Override
    public void tagStock(String strategyIdentifier) {
        TagMarker tagMarker = tagMarkerService.findByName(strategyIdentifier);
        if(tagMarker.isGlobal()){
            tagMarker.processGlobal();
        }else{
            tagMarker.cleanTags("日");
            tagMarker.cleanTags("月");
            tagMarker.cleanTags("周");

            if(tagMarker.supportDate()){
                for (StockEntity stockEntity : stockService.findAll()) {
                    StockHistoryData stockHistoryData = dayDataService.findStockHistoryData(stockEntity);
                    if(stockHistoryData.getCloseArray().length>20){
                        tagMarker.processEachStock(stockHistoryData, stockEntity, "日");
                    }
                }
            }
            if(tagMarker.supportWeek()){
                for (StockEntity stockEntity : stockService.findAll()) {
                    StockHistoryData stockHistoryData = weekDataService.findStockHistoryData(stockEntity);
                    if(stockHistoryData.getCloseArray().length>20){
                        tagMarker.processEachStock(stockHistoryData, stockEntity, "周");
                    }
                }
            }
            if(tagMarker.supportMonth()){
                for (StockEntity stockEntity : stockService.findAll()) {
                    StockHistoryData stockHistoryData = monthDataService.findStockHistoryData(stockEntity);
                    if(stockHistoryData.getCloseArray().length>20){
                        tagMarker.processEachStock(stockHistoryData, stockEntity, "月");
                    }
                }
            }
        }
    }

    @Override
    public void enable(long id) {
        StockStrategyEntity stockStrategyEntity = this.get(id);
        stockStrategyEntity.setEnable(true);
        this.update(stockStrategyEntity);
    }

    @Override
    public void disable(long id) {
        StockStrategyEntity stockStrategyEntity = this.get(id);
        stockStrategyEntity.setEnable(false);
        this.update(stockStrategyEntity);
    }

    @Override
    public void processWithMonthTimer() {

        //1. 处理全局标签处理器
        List<TagMarker> globalTagMarkers = tagMarkerService.findMonthGlobalMarkers();
        for (TagMarker tagMarker : globalTagMarkers) {
            tagMarker.processGlobal();
            StockStrategyEntity stockStrategyEntity = stockStrategyDao.findByIdentifier(tagMarker.getClass().getSimpleName());
            if(stockStrategyEntity!=null){
                stockStrategyEntity.setLastExecuteTime(new Date());
                stockStrategyDao.save(stockStrategyEntity);
            }
        }

        //2. 清理个股月级别历史标签
        List<TagMarker> eachProcessors = tagMarkerService.findMonthEachMarkers();
        for (TagMarker eachProcessor : eachProcessors) {
            eachProcessor.cleanTags("月");
        }

        taskTracerImpl.trace(TaskType.Month_STRATEGY, taskCounter -> {
            for (StockEntity stockEntity : stockService.findAll()) {
                StockHistoryData stockHistoryData = monthDataService.findStockHistoryData(stockEntity);
                if(stockHistoryData.getCloseArray().length<20){
                    continue;
                }
                for (TagMarker eachProcessor : eachProcessors) {
                    eachProcessor.processEachStock(stockHistoryData, stockEntity,"月");
                }
                taskCounter.increase();
            }
        });

        for (TagMarker eachProcessor : eachProcessors) {
            StockStrategyEntity stockStrategyEntity = stockStrategyDao.findByIdentifier(eachProcessor.getClass().getSimpleName());
            if(stockStrategyEntity!=null){
                stockStrategyEntity.setLastExecuteTime(new Date());
                stockStrategyDao.save(stockStrategyEntity);
            }
        }

    }

    @Override
    public void processWithDayTimer() {

        List<TagMarker> globalTagMarkers = tagMarkerService.findDayGlobalMarker();
        for (TagMarker globalTagProcessor : globalTagMarkers) {
            globalTagProcessor.processGlobal();
            StockStrategyEntity stockStrategyEntity = stockStrategyDao.findByIdentifier(globalTagProcessor.getClass().getSimpleName());
            if(stockStrategyEntity!=null){
                stockStrategyEntity.setLastExecuteTime(new Date());
                stockStrategyDao.save(stockStrategyEntity);
            }
        }

        List<TagMarker> tagMarkers = tagMarkerService.findDayEachProcessors();

        for (TagMarker eachProcessor : tagMarkers) {
            eachProcessor.cleanTags("日");
        }

        taskTracerImpl.trace(TaskType.Day_STRATEGY, taskCounter -> {
            for (StockEntity stockEntity : stockService.findAll()) {
                taskCounter.increase();
                StockHistoryData stockHistoryData = dayDataService.findStockHistoryData(stockEntity);
                if (stockHistoryData.getCloseArray().length < 20) {
                    continue;
                }
                for (TagMarker tagMarker : tagMarkers) {
                    tagMarker.processEachStock(stockHistoryData, stockEntity, "日");
                }
            }
        });

        for (TagMarker eachProcessor : tagMarkers) {
            StockStrategyEntity stockStrategyEntity = stockStrategyDao.findByIdentifier(eachProcessor.getClass().getSimpleName());
            if(stockStrategyEntity!=null){
                stockStrategyEntity.setLastExecuteTime(new Date());
                stockStrategyDao.save(stockStrategyEntity);
            }
        }

    }

    @Override
    public void processWithWeekTimer() {

        List<TagMarker> globalTagMarkers = tagMarkerService.findWeekGlobalMarkers();
        for (TagMarker tagMarker : globalTagMarkers) {
            tagMarker.processGlobal();
            StockStrategyEntity stockStrategyEntity = stockStrategyDao.findByIdentifier(tagMarker.getClass().getSimpleName());
            if(stockStrategyEntity!=null){
                stockStrategyEntity.setLastExecuteTime(new Date());
                stockStrategyDao.save(stockStrategyEntity);
            }
        }

        List<TagMarker> weekEachMarkers = tagMarkerService.findWeekEachMarkers();

        for (TagMarker eachProcessor : weekEachMarkers) {
            eachProcessor.cleanTags("周");
        }

        taskTracerImpl.trace(TaskType.Week_STRATEGY, taskCounter -> {
            for (StockEntity stockEntity : stockService.findAll()) {
                taskCounter.increase();
                StockHistoryData stockHistoryData = weekDataService.findStockHistoryData(stockEntity);
                if(stockHistoryData.getCloseArray().length<20){
                    continue;
                }
                for (TagMarker tagMarker : weekEachMarkers) {
                    tagMarker.processEachStock(stockHistoryData, stockEntity, "周");
                }
            }
        });


        for (TagMarker eachProcessor : tagMarkerService.findWeekEachMarkers()) {
            StockStrategyEntity stockStrategyEntity = stockStrategyDao.findByIdentifier(eachProcessor.getClass().getSimpleName());
            if(stockStrategyEntity!=null){
                stockStrategyEntity.setLastExecuteTime(new Date());
                stockStrategyDao.save(stockStrategyEntity);
            }
        }

    }

}