package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.StockStrategyDao;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.enu.StrategyType;
import io.philoyui.qmier.qmiermanager.service.*;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilters;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagMarker;
import io.philoyui.qmier.qmiermanager.service.tag.TagMarkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

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
    private StockFilters stockFilters;

    @Override
    protected GenericDao<StockStrategyEntity, Long> getDao() {
        return stockStrategyDao;
    }

    @Override
    public void tagStock(StockStrategyEntity stockStrategyEntity) {

        tagStockService.deleteByTagName(stockStrategyEntity.getName());

        TagMarker tagMarker = tagMarkerService.findByName(stockStrategyEntity.getIdentifier());
        if(tagMarker.isGlobal()){
            tagMarker.processGlobal();
        }else{
            if(tagMarker.supportDate()){
                for (StockEntity stockEntity : stockService.findAll()) {
                    ProcessorContext processorContext = new ProcessorContext();
                    processorContext.setLowDataArray(dayDataService.findLowData(stockEntity));
                    processorContext.setHighDataArray(dayDataService.findHighData(stockEntity));
                    processorContext.setCloseDataArray(dayDataService.findCloseData(stockEntity));
                    processorContext.setOpenDataArray(dayDataService.findOpenData(stockEntity));
                    processorContext.setVolumeDataArray(dayDataService.findVolumeData(stockEntity));
                    tagMarker.processEachStock(processorContext, stockEntity);
                }
            }
            if(tagMarker.supportWeek()){
                for (StockEntity stockEntity : stockService.findAll()) {
                    ProcessorContext processorContext = new ProcessorContext();
                    processorContext.setLowDataArray(weekDataService.findLowData(stockEntity));
                    processorContext.setHighDataArray(weekDataService.findHighData(stockEntity));
                    processorContext.setCloseDataArray(weekDataService.findCloseData(stockEntity));
                    processorContext.setOpenDataArray(weekDataService.findOpenData(stockEntity));
                    processorContext.setVolumeDataArray(weekDataService.findVolumeData(stockEntity));
                    tagMarker.processEachStock(processorContext, stockEntity);
                }
            }
            if(tagMarker.supportMonth()){
                for (StockEntity stockEntity : stockService.findAll()) {
                    ProcessorContext processorContext = new ProcessorContext();
                    processorContext.setLowDataArray(monthDataService.findLowData(stockEntity));
                    processorContext.setHighDataArray(monthDataService.findHighData(stockEntity));
                    processorContext.setCloseDataArray(monthDataService.findCloseData(stockEntity));
                    processorContext.setOpenDataArray(monthDataService.findOpenData(stockEntity));
                    processorContext.setVolumeDataArray(monthDataService.findVolumeData(stockEntity));
                    tagMarker.processEachStock(processorContext, stockEntity);
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
    public List<StockStrategyEntity> findAdd() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        searchFilter.add(Restrictions.eq("strategyType", StrategyType.ADD));
        return list(searchFilter);
    }

    @Override
    public List<StockStrategyEntity> findReduce() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        searchFilter.add(Restrictions.eq("strategyType", StrategyType.REDUCE));
        return list(searchFilter);
    }

    @Override
    public void dropStock(StockStrategyEntity stockStrategyEntity) {
        StockFilter stockFilter = stockFilters.select(stockStrategyEntity.getIdentifier());
        Set<String> codeSet = stockFilter.filterSymbol(stockStrategyEntity);
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.in("symbol",codeSet.toArray(new String[0])));
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        for (StockEntity stockEntity : stockEntities) {
            stockEntity.setEnable(false);
        }
        stockService.updateAll(stockEntities);
    }

    @Override
    public List<StockStrategyEntity> findByIntervalType(IntervalType intervalType) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", intervalType));
        return list(searchFilter);
    }

    @Override
    public void processWithMonthTimer() {

        List<TagMarker> globalTagMarkers = tagMarkerService.findMonthGlobalMarkers();
        for (TagMarker tagMarker : globalTagMarkers) {
            tagMarker.processGlobal();
        }

        List<TagMarker> eachProcessors = tagMarkerService.findMonthEachMarkers();
        for (StockEntity stockEntity : stockService.findAll()) {
            ProcessorContext processorContext = new ProcessorContext();
            processorContext.setLowDataArray(monthDataService.findLowData(stockEntity));
            processorContext.setHighDataArray(monthDataService.findHighData(stockEntity));
            processorContext.setCloseDataArray(monthDataService.findCloseData(stockEntity));
            processorContext.setOpenDataArray(monthDataService.findOpenData(stockEntity));
            processorContext.setVolumeDataArray(monthDataService.findVolumeData(stockEntity));
            for (TagMarker eachProcessor : eachProcessors) {
                eachProcessor.processEachStock(processorContext, stockEntity);
            }
        }

    }

    @Override
    public void processWithDayTimer() {

        List<TagMarker> globalTagMarkers = tagMarkerService.findDayGlobalMarker();
        for (TagMarker globalTagProcessor : globalTagMarkers) {
            globalTagProcessor.processGlobal();
        }

        for (StockEntity stockEntity : stockService.findAll()) {
            ProcessorContext processorContext = new ProcessorContext();
            processorContext.setLowDataArray(dayDataService.findLowData(stockEntity));
            processorContext.setHighDataArray(dayDataService.findHighData(stockEntity));
            processorContext.setCloseDataArray(dayDataService.findCloseData(stockEntity));
            processorContext.setOpenDataArray(dayDataService.findOpenData(stockEntity));
            processorContext.setVolumeDataArray(dayDataService.findVolumeData(stockEntity));
            for (TagMarker tagMarker : tagMarkerService.findDayEachProcessors()) {
                tagMarker.processEachStock(processorContext, stockEntity);
            }
        }

    }

    @Override
    public void processWithWeekTimer() {

        List<TagMarker> tagMarkers = tagMarkerService.findWeekGlobalMarkers();
        for (TagMarker tagMarker : tagMarkers) {
            tagMarker.processGlobal();
        }

        List<TagMarker> weekEachMarkers = tagMarkerService.findWeekEachMarkers();
        for (StockEntity stockEntity : stockService.findAll()) {
            ProcessorContext processorContext = new ProcessorContext();
            processorContext.setLowDataArray(weekDataService.findLowData(stockEntity));
            processorContext.setHighDataArray(weekDataService.findHighData(stockEntity));
            processorContext.setCloseDataArray(weekDataService.findCloseData(stockEntity));
            processorContext.setOpenDataArray(weekDataService.findOpenData(stockEntity));
            processorContext.setVolumeDataArray(weekDataService.findVolumeData(stockEntity));
            for (TagMarker tagMarker : weekEachMarkers) {
                tagMarker.processEachStock(processorContext, stockEntity);
            }
        }

    }

}