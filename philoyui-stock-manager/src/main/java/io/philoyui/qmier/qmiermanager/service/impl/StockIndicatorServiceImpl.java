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
    public void executeDayTask() {
        //清理所有日线级别数据
        dayDataService.deleteAll();

        //清理指标数据
        List<StockIndicatorEntity> dayStockIndicators  = this.findDayEnable();
        for (StockIndicatorEntity dayStockIndicator : dayStockIndicators) {
            IndicatorProvider dayIndicatorProvider = indicatorProviders.findByIdentifier(dayStockIndicator.getIdentifier());
            dayIndicatorProvider.cleanOldData();
            dayIndicatorProvider.processGlobal();
        }

        //遍历所有的股票，下载历史数据，执行python脚本生成指标数据，找到指标处理器生成为股票打标，并记录打标日志
        for (StockEntity stockEntity : stockService.findAll()) {
            System.out.println("下载股票" + stockEntity.getSymbol());
            dayDataService.downloadHistory(stockEntity);
            List<TagStockEntity> tagStockEntities= new ArrayList<>();
            for (StockIndicatorEntity dayStockIndicator : dayStockIndicators) {
                System.out.println("使用指标" + dayStockIndicator.getIdentifier());
                parseIndicatorDataUsePython(dayStockIndicator.getPythonName(),stockEntity.getSymbol(),"Day");
                IndicatorProvider dayIndicatorProvider = indicatorProviders.findByIdentifier(dayStockIndicator.getIdentifier());
                List<TagStockEntity> tagEntityList = dayIndicatorProvider.processTags(stockEntity);
                if(tagEntityList!=null){
                    tagStockEntities.addAll(tagEntityList);
                }
            }
            taskLogService.logDownloadSuccess(stockEntity,tagStockEntities);
        }

    }

    @Override
    public void executeWeekTask() {

        //清理所有日线级别数据
        weekDataService.deleteAll();

        //清理指标数据
        List<StockIndicatorEntity> weekStockIndicators  = this.findWeekEnable();
        for (StockIndicatorEntity weekStockIndicator : weekStockIndicators) {
            IndicatorProvider weekIndicatorProvider = indicatorProviders.findByIdentifier(weekStockIndicator.getIdentifier());
            weekIndicatorProvider.cleanOldData();
            weekIndicatorProvider.processGlobal();
        }

        //遍历所有的股票，下载历史数据，执行python脚本生成指标数据，找到指标处理器生成为股票打标，并记录打标日志
        for (StockEntity stockEntity : stockService.findAll()) {
            System.out.println("下载股票" + stockEntity.getSymbol());
            weekDataService.downloadHistory(stockEntity);
            List<TagStockEntity> tagStockEntities= new ArrayList<>();
            for (StockIndicatorEntity weekStockIndicator : weekStockIndicators) {
                System.out.println("使用指标" + weekStockIndicator.getIdentifier());
                parseIndicatorDataUsePython(weekStockIndicator.getPythonName(),stockEntity.getSymbol(),"Week");
                IndicatorProvider weekIndicatorProvider = indicatorProviders.findByIdentifier(weekStockIndicator.getIdentifier());
                List<TagStockEntity> tagEntityList = weekIndicatorProvider.processTags(stockEntity);
                if(tagEntityList!=null){
                    tagStockEntities.addAll(tagEntityList);
                }
            }
            taskLogService.logDownloadSuccess(stockEntity,tagStockEntities);
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
            monthIndicatorProvider.processGlobal();
        }

        //遍历所有的股票，下载历史数据，执行python脚本生成指标数据，找到指标处理器生成为股票打标，并记录打标日志
        for (StockEntity stockEntity : stockService.findAll()) {
            System.out.println("下载股票" + stockEntity.getSymbol());
            monthDataService.downloadHistory(stockEntity);
            List<TagStockEntity> tagStockEntities= new ArrayList<>();
            for (StockIndicatorEntity monthStockIndicator : monthStockIndicators) {
                System.out.println("使用指标" + monthStockIndicator.getIdentifier());
                parseIndicatorDataUsePython(monthStockIndicator.getPythonName(),stockEntity.getSymbol(),"Month");
                IndicatorProvider monthIndicatorProvider = indicatorProviders.findByIdentifier(monthStockIndicator.getIdentifier());
                List<TagStockEntity> tagEntityList = monthIndicatorProvider.processTags(stockEntity);
                if(tagEntityList!=null){
                    tagStockEntities.addAll(tagEntityList);
                }
            }
            taskLogService.logDownloadSuccess(stockEntity,tagStockEntities);
        }
    }

    private void parseIndicatorDataUsePython(String pythonName, String symbol, String interval) {
        Process process;
        try {
            process = Runtime.getRuntime().exec(pythonPath + pythonName + " " + symbol + " " + interval);// 执行py文件

//            process = Runtime.getRuntime().exec("python /root/python/" + pythonName + " " + symbol + " " + interval);// 执行py文件
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
