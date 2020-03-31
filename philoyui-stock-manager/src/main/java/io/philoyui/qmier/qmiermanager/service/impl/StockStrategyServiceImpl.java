package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.dao.StockStrategyDao;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.enu.StrategyType;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilters;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessor;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class StockStrategyServiceImpl extends GenericServiceImpl<StockStrategyEntity,Long> implements StockStrategyService {

    @Autowired
    private StockStrategyDao stockStrategyDao;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockFilters stockFilters;

    @Autowired
    private TagProcessorService tagProcessorService;

    @Override
    protected GenericDao<StockStrategyEntity, Long> getDao() {
        return stockStrategyDao;
    }

    @Override
    public void tagStock(StockStrategyEntity stockStrategyEntity) {

        tagStockService.deleteByTagName(stockStrategyEntity.getName());

        TagProcessor tagProcessor = tagProcessorService.findByName(stockStrategyEntity.getIdentifier());

        if(tagProcessor!=null){
            tagStockService.deleteByTagName(stockStrategyEntity.getName());

            tagProcessorService.processDay(Lists.newArrayList(tagProcessor));

            stockStrategyEntity.setLastExecuteTime(new Date());
            stockStrategyDao.save(stockStrategyEntity);
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
        List<StockStrategyEntity> weekStockStrategies = this.findByIntervalType(IntervalType.Month);
        for (StockStrategyEntity weekStockStrategy : weekStockStrategies) {
            this.tagStock(weekStockStrategy);
        }

        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python3 /root/python/month_task.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processWithDayTimer() {
        List<StockStrategyEntity> weekStockStrategies = this.findByIntervalType(IntervalType.Day);
        for (StockStrategyEntity weekStockStrategy : weekStockStrategies) {
            this.tagStock(weekStockStrategy);
        }

        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python3 /root/python/day_task.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processWithWeekTimer() {

        List<StockStrategyEntity> weekStockStrategies = this.findByIntervalType(IntervalType.Week);
        for (StockStrategyEntity weekStockStrategy : weekStockStrategies) {
            this.tagStock(weekStockStrategy);
        }

        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python3 /root/python/week_task.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}