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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class StockIndicatorServiceImpl extends GenericServiceImpl<StockIndicatorEntity,Long> implements StockIndicatorService {

    @Value("${application.python.path}")
    private String pythonPath;

    @Autowired
    private StockIndicatorDao stockIndicatorDao;

    @Autowired
    private StockService stockService;

    @Autowired
    private DayDataService dayDataService;

    @Autowired
    private WeekDataService weekDataService;

    @Autowired
    private MonthDataService monthDataService;

    @Autowired
    private IndicatorProviders indicatorProviders;

    @Autowired
    private TaskLogService taskLogService;

    @Autowired
    private MyStockService myStockService;

    private ExecutorService executorService = Executors.newFixedThreadPool(4);

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
    public List<StockIndicatorEntity> findMonthEnable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Month));
        return this.list(searchFilter);
    }

    @Override
    public List<StockIndicatorEntity> findMin30Enable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("enable",true));
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Min30));
        return this.list(searchFilter);
    }

    @Override
    public void executeDayTask() {

        List<StockIndicatorEntity> dayStockIndicators  = this.findDayEnable();

        //清理所有的指标数据
        for (StockIndicatorEntity dayStockIndicator : dayStockIndicators) {
            IndicatorProvider dayIndicatorProvider = indicatorProviders.findByIdentifier(dayStockIndicator.getIdentifier());
            dayIndicatorProvider.cleanOldData();
        }

        //遍历股票
        for (StockEntity stockEntity : stockService.findAll()) {
            if(stockEntity.getName().contains("ST")) {
                continue;
            }
            System.out.println("下载数据" + stockEntity.getSymbol());
            //提取指标数据脚本
            parseIndicatorDataUsePython("abstract_indicator.py",stockEntity.getSymbol(),"Day");
            List<TagStockEntity> tagStockEntities = new ArrayList<>();
            //解析指标，打标
            for (StockIndicatorEntity dayStockIndicator : dayStockIndicators) {
                IndicatorProvider dayIndicatorProvider = indicatorProviders.findByIdentifier(dayStockIndicator.getIdentifier());
                List<TagStockEntity> tagEntityList = dayIndicatorProvider.processTags(stockEntity);
                if(tagEntityList!=null){
                    tagStockEntities.addAll(tagEntityList);
                }
            }
        }

        //执行全局指标
        for (StockIndicatorEntity dayStockIndicator : dayStockIndicators) {
            IndicatorProvider dayIndicatorProvider = indicatorProviders.findByIdentifier(dayStockIndicator.getIdentifier());
            dayIndicatorProvider.processGlobal();
        }

        myStockService.obtainEveryDay();

    }

    @Override
    public void executeWeekTask() {

        weekDataService.deleteAll();

        List<StockIndicatorEntity> weekStockIndicators  = this.findWeekEnable();

        for (StockIndicatorEntity weekStockIndicator : weekStockIndicators) {
            IndicatorProvider weekIndicatorProvider = indicatorProviders.findByIdentifier(weekStockIndicator.getIdentifier());
            weekIndicatorProvider.cleanOldData();
        }

        for (StockEntity stockEntity : stockService.findAll()) {
            if(stockEntity.getName().contains("ST")) {
                continue;
            }
            executorService.execute(() -> {
                System.out.println("下载股票" + stockEntity.getSymbol());
                weekDataService.downloadHistory(stockEntity);
                List<TagStockEntity> tagStockEntities = new ArrayList<>();
                for (StockIndicatorEntity weekStockIndicator : weekStockIndicators) {
                    parseIndicatorDataUsePython(weekStockIndicator.getPythonName(), stockEntity.getSymbol(), "Week");
                    IndicatorProvider weekIndicatorProvider = indicatorProviders.findByIdentifier(weekStockIndicator.getIdentifier());
                    List<TagStockEntity> tagEntityList = weekIndicatorProvider.processTags(stockEntity);
                    if (tagEntityList != null) {
                        tagStockEntities.addAll(tagEntityList);
                    }
                }
            });
        }

        for (StockIndicatorEntity weekStockIndicator : weekStockIndicators) {
            IndicatorProvider weekIndicatorProvider = indicatorProviders.findByIdentifier(weekStockIndicator.getIdentifier());
            weekIndicatorProvider.processGlobal();
        }
    }

    @Override
    public void executeMonthTask() {
        //清理所有日线级别数据
        monthDataService.deleteAll();

        //清理指标数据
        List<StockIndicatorEntity> monthStockIndicators  = this.findMonthEnable();

        for (StockIndicatorEntity monthStockIndicator : monthStockIndicators) {
            IndicatorProvider monthIndicatorProvider = indicatorProviders.findByIdentifier(monthStockIndicator.getIdentifier());
            monthIndicatorProvider.cleanOldData();
        }

        //遍历所有的股票，下载历史数据，执行python脚本生成指标数据，找到指标处理器生成为股票打标，并记录打标日志
        for (StockEntity stockEntity : stockService.findAll()) {
            if(stockEntity.getName().contains("ST")) {
                continue;
            }
            executorService.execute(() -> {
                System.out.println("下载股票" + stockEntity.getSymbol());
                monthDataService.downloadHistory(stockEntity);
                List<TagStockEntity> tagStockEntities = new ArrayList<>();
                for (StockIndicatorEntity monthStockIndicator : monthStockIndicators) {
                    parseIndicatorDataUsePython(monthStockIndicator.getPythonName(), stockEntity.getSymbol(), "Month");
                    IndicatorProvider monthIndicatorProvider = indicatorProviders.findByIdentifier(monthStockIndicator.getIdentifier());
                    List<TagStockEntity> tagEntityList = monthIndicatorProvider.processTags(stockEntity);
                    if (tagEntityList != null) {
                        tagStockEntities.addAll(tagEntityList);
                    }
                }
            });
        }

        for (StockIndicatorEntity monthStockIndicator : monthStockIndicators) {
            IndicatorProvider monthIndicatorProvider = indicatorProviders.findByIdentifier(monthStockIndicator.getIdentifier());
            monthIndicatorProvider.cleanOldData();
            monthIndicatorProvider.processGlobal();
        }
    }

    @Override
    public void executeGlobal(Long id) {
        StockIndicatorEntity stockIndicatorEntity = stockIndicatorDao.getOne(id);
        IndicatorProvider monthIndicatorProvider = indicatorProviders.findByIdentifier(stockIndicatorEntity.getIdentifier());
        monthIndicatorProvider.cleanOldData();
        monthIndicatorProvider.processGlobal();
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
