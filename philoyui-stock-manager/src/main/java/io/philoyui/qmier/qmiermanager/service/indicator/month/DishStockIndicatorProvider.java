package io.philoyui.qmier.qmiermanager.service.indicator.month;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DishStockIndicatorProvider implements IndicatorProvider {

    @Autowired
    private StockService stockService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        return null;
    }

    @Override
    public String identifier() {
        return "dish_stock";
    }

    @Override
    public void cleanOldData() {
        tagStockService.deleteByTagName("大盘股");
        tagStockService.deleteByTagName("中盘股");
        tagStockService.deleteByTagName("小盘股");
    }

    @Override
    public void processGlobal() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gt("totalPrice",1000000));
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        List<String> symbolLists = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(symbolLists,"大盘股",new Date(), IntervalType.Month);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lte("totalPrice",1000000));
        searchFilter.add(Restrictions.gte("totalPrice",400000));
        stockEntities = stockService.list(searchFilter);
        symbolLists = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(symbolLists,"中盘股",new Date(), IntervalType.Month);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lt("totalPrice",400000));
        stockEntities = stockService.list(searchFilter);
        symbolLists = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(symbolLists,"小盘股",new Date(), IntervalType.Month);
    }
}
