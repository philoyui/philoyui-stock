package io.philoyui.qmier.qmiermanager.service.filter;

import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StockFilters {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TagStockService tagStockService;

    private Map<String,StockFilter> stockFilterMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void start(){
        Map<String, StockFilter> beanNamesStockFilters = applicationContext.getBeansOfType(StockFilter.class);
        for (StockFilter stockFilter : beanNamesStockFilters.values()) {
            stockFilterMap.put(stockFilter.filterName(),stockFilter);
        }
    }

    public StockFilter select(String identifier) {
        return stockFilterMap.get(identifier);
    }

    /**
     * 保存标签的关联关系
     * @param tagName           标签名称
     * @param filterStockSet    股票列表
     */
    private void persistNewTagStock(String tagName, Set<String> filterStockSet) {
        tagStockService.deleteByTagName(tagName);
        List<TagStockEntity> tagStockEntities = new ArrayList<>();
        for (String stockString : filterStockSet) {
            TagStockEntity tagStockEntity = new TagStockEntity();
            tagStockEntity.setSymbol(stockString);
            tagStockEntity.setTagName(tagName);
            tagStockEntity.setCreatedTime(new Date());
            tagStockEntities.add(tagStockEntity);
        }
        tagStockService.batchInsert(tagStockEntities);
    }

    public Set<String> selectStocks(StockStrategyEntity stockStrategyEntity) {
        StockFilter stockFilter = stockFilterMap.get(stockStrategyEntity.getIdentifier());
        return stockFilter.filterSymbol(stockStrategyEntity);
    }
}
