package io.philoyui.qmier.qmiermanager.service.tag.processor;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.tag.ProcessorContext;
import io.philoyui.qmier.qmiermanager.service.tag.TagProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 大盘股，小盘股
 */
@Component
public class DishStockTagProcessor extends TagProcessor {

    @Autowired
    private StockService stockService;

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gt("totalPrice",1000000));
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        this.tagStocks(stockEntities,"大盘股");

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lte("totalPrice",1000000));
        searchFilter.add(Restrictions.gte("totalPrice",400000));
        stockEntities = stockService.list(searchFilter);
        this.tagStocks(stockEntities,"中盘股");

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lt("totalPrice",400000));
        stockEntities = stockService.list(searchFilter);
        this.tagStocks(stockEntities,"小盘股");

    }

}
