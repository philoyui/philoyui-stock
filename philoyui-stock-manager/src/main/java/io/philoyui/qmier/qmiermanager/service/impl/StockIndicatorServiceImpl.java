package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.StockIndicatorDao;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockIndicatorEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
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
public class StockIndicatorServiceImpl extends GenericServiceImpl<StockIndicatorEntity,Long> implements StockIndicatorService {

    @Autowired
    private StockIndicatorDao stockIndicatorDao;

    @Autowired
    private StockService stockService;

    @Autowired
    private DayDataService dayDataService;

    @Autowired
    private WeekDataService weekDataService;

    @Autowired
    private IndicatorProviders indicatorProviders;

    @Autowired
    private TaskLogService taskLogService;

    @Override
    protected GenericDao<StockIndicatorEntity, Long> getDao() {
        return stockIndicatorDao;
    }

    @Override
    public List<StockIndicatorEntity> findDayEnable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        return this.list(searchFilter);
    }

    @Override
    public List<StockIndicatorEntity> findWeekEnable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Week));
        return this.list(searchFilter);
    }

    @Override
    public void executeDayTask() {
        dayDataService.deleteAll();
        List<StockEntity> stockEntities = stockService.findAll();
        for (StockEntity stockEntity : stockEntities) {
            dayDataService.downloadHistory(stockEntity);
            List<StockIndicatorEntity> dayStockIndicators  = this.findDayEnable();
            List<TagStockEntity> tagStockEntities= new ArrayList<>();
            for (StockIndicatorEntity dayStockIndicator : dayStockIndicators) {
                IndicatorProvider dayIndicatorProvider = indicatorProviders.findByIdentifier(dayStockIndicator.getIdentifier());
                dayIndicatorProvider.cleanOldData(stockEntity.getSymbol());
                parseIndicatorDataUsePython(dayStockIndicator.getPythonName(),stockEntity.getSymbol(),"Day");
                List<TagStockEntity> tagEntityList = dayIndicatorProvider.processTags(stockEntity);
                tagStockEntities.addAll(tagEntityList);
            }
            taskLogService.logDownloadSuccess(stockEntity,tagStockEntities);
        }
    }

    @Override
    public void executeWeekTask() {
        weekDataService.deleteAll();
        List<StockEntity> stockEntities = stockService.findAll();
        for (StockEntity stockEntity : stockEntities) {
            weekDataService.downloadHistory(stockEntity);
            List<StockIndicatorEntity> weekStockIndicators  = this.findWeekEnable();
            List<TagStockEntity> tagStockEntities= new ArrayList<>();
            for (StockIndicatorEntity weekStockIndicator : weekStockIndicators) {
                IndicatorProvider weekIndicatorProvider = indicatorProviders.findByIdentifier(weekStockIndicator.getIdentifier());
                weekIndicatorProvider.cleanOldData(stockEntity.getSymbol());
                parseIndicatorDataUsePython(weekStockIndicator.getPythonName(),stockEntity.getSymbol(),"Week");
                List<TagStockEntity> tagEntityList = weekIndicatorProvider.processTags(stockEntity);
                tagStockEntities.addAll(tagEntityList);
            }
            taskLogService.logDownloadSuccess(stockEntity,tagStockEntities);
        }
    }

    @Override
    public void executeMonthTask() {

    }

    private void parseIndicatorDataUsePython(String pythonName, String symbol, String interval) {
        Process process;
        try {
            process = Runtime.getRuntime().exec("python /root/python/" + pythonName + " " + symbol + " " + interval);// 执行py文件
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
