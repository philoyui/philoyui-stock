package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Sets;
import io.philoyui.qmier.qmiermanager.dao.MyStockDao;
import io.philoyui.qmier.qmiermanager.entity.ChooseDefinitionEntity;
import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.service.ChooseDefinitionService;
import io.philoyui.qmier.qmiermanager.service.FilterDefinitionService;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilters;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
    private MyStockService myStockService;

    @Autowired
    private ChooseDefinitionService chooseDefinitionService;

    @Autowired
    private FilterDefinitionService filterDefinitionService;

    @Override
    protected GenericDao<MyStockEntity, Long> getDao() {
        return myStockDao;
    }

    public void obtainEveryDay(){

        //1. 删除今天的自选股
        String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        myStockDao.deleteByDateString(dateStr);

        //2. 获取所有的筛选股票池
        List<ChooseDefinitionEntity> chooseDefinitionEntities = chooseDefinitionService.findByEnable(true);

        //3. 股票池，选择
        Set<String> selectedStockSet = new HashSet<>();
        for (ChooseDefinitionEntity chooseDefinitionEntity : chooseDefinitionEntities) {
            StockFilter stockFilter = stockFilters.select(chooseDefinitionEntity.getIdentifier());
            Set<String> filterStockSet = stockFilter.filterSymbol(chooseDefinitionEntity.getParam1(), chooseDefinitionEntity.getParam2(), chooseDefinitionEntity.getParam3());
            selectedStockSet = Sets.union(selectedStockSet,filterStockSet);
        }

        //4. 根据条件过滤股票
        List<FilterDefinitionEntity> filterDefinitionEntities = filterDefinitionService.findByEnable(true);
        for (FilterDefinitionEntity filterDefinitionEntity : filterDefinitionEntities) {
            StockFilter stockFilter = stockFilters.select(filterDefinitionEntity.getIdentifier());
            Set<String> filterStockSet = stockFilter.filterSymbol(filterDefinitionEntity.getParam1(), filterDefinitionEntity.getParam2(), filterDefinitionEntity.getParam3());
            selectedStockSet = Sets.intersection(selectedStockSet,filterStockSet);
        }

        for (String symbol : selectedStockSet) {
            MyStockEntity myStockEntity = new MyStockEntity();
            myStockEntity.setSymbol(symbol);
            myStockEntity.setCreatedTime(new Date());
            myStockEntity.setDateString(dateStr);
            myStockService.insert(myStockEntity);
        }

    }

}
