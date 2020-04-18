package io.philoyui.qmier.qmiermanager.service.tag.processor;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.tag.GlobalTagMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 大盘股，小盘股
 */
@Component
public class DishStockTagMarker extends GlobalTagMarker {

    @Autowired
    private StockService stockService;

    @Override
    public void processGlobal() {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gt("totalPrice",1000000));
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        List<String> symbolLists = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toList());
        this.tagStocks(symbolLists,"大盘股");

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lte("totalPrice",1000000));
        searchFilter.add(Restrictions.gte("totalPrice",400000));
        stockEntities = stockService.list(searchFilter);
        symbolLists = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toList());
        this.tagStocks(symbolLists,"中盘股");

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lt("totalPrice",400000));
        stockEntities = stockService.list(searchFilter);
        symbolLists = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toList());
        this.tagStocks(symbolLists,"小盘股");

    }

    @Override
    public boolean supportDate() {
        return false;
    }

    @Override
    public boolean supportWeek() {
        return false;
    }

    @Override
    public boolean supportMonth() {
        return true;
    }

}
