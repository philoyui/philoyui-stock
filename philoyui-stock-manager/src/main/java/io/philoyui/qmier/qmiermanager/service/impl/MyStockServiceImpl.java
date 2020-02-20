package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Sets;
import io.philoyui.qmier.qmiermanager.dao.MyStockDao;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilters;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MyStockServiceImpl extends GenericServiceImpl<MyStockEntity,Long> implements MyStockService {

    @Autowired
    private StockFilters stockFilters;

    @Autowired
    private MyStockDao myStockDao;

    @Autowired
    private StockStrategyService stockStrategyService;

    @Override
    protected GenericDao<MyStockEntity, Long> getDao() {
        return myStockDao;
    }

    @Transactional
    public void obtainEveryDay(){

        //1. 删除今天的自选股
        myStockDao.deleteAll();

        //2. 获取所有的筛选股票池
        List<StockStrategyEntity> addStrategies = stockStrategyService.findAdd();

        //3. 股票池，选择
        Set<String> selectedStockSet = new HashSet<>();
        for (StockStrategyEntity addStrategy : addStrategies) {
            Set<String> filterStockSet = stockFilters.selectStocks(addStrategy);
            selectedStockSet = Sets.union(selectedStockSet,filterStockSet);
        }

        //4. 根据条件过滤股票
        List<StockStrategyEntity> reduceStrategies = stockStrategyService.findReduce();
        for (StockStrategyEntity reduceStrategy : reduceStrategies) {
            Set<String> filterStockSet = stockFilters.selectStocks(reduceStrategy);
            selectedStockSet = Sets.difference(selectedStockSet,filterStockSet);
        }

        String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        for (String symbol : selectedStockSet) {
            MyStockEntity myStockEntity = new MyStockEntity();
            myStockEntity.setSymbol(symbol);
            myStockEntity.setCreatedTime(new Date());
            myStockEntity.setDateString(dateStr);
            myStockDao.save(myStockEntity);
        }

    }



}
