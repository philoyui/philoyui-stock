package io.philoyui.qmier.qmiermanager.service.filter;

import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.impl.PythonStockFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class StockFilters {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private PythonStockFilter pythonStockFilter;

    private Map<String,StockFilter> stockFilterMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void start(){
        Map<String, StockFilter> beanNamesStockFilters = applicationContext.getBeansOfType(StockFilter.class);
        for (StockFilter stockFilter : beanNamesStockFilters.values()) {
            stockFilterMap.put(stockFilter.filterName(),stockFilter);
        }
    }

    public StockFilter select(String identifier) {
        StockFilter stockFilter = stockFilterMap.get(identifier);
        if(stockFilter==null){
            return pythonStockFilter;
        }
        return stockFilterMap.get(identifier);
    }

    public Set<String> selectStocks(StockStrategyEntity stockStrategyEntity) {
        StockFilter stockFilter = this.select(stockStrategyEntity.getIdentifier());
        return stockFilter.filterSymbol(stockStrategyEntity);
    }
}
