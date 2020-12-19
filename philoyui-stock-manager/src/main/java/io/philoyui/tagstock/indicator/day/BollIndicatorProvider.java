package io.philoyui.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.BollDataEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import io.philoyui.tagstock.service.BollDataService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BollIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private BollDataService bollDataService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<BollDataEntity> bollDataEntities = bollDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        for (BollDataEntity bollDataEntity : bollDataEntities) {
            switch (bollDataEntity.getBollType()){
                case EXPAND:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
                case SHRINK:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
                case FALL_THROUGH_LOWER:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
                case FALL_THROUGH_UPPER:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
                case BREAK_THROUGH_LOWER:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
                case BREAK_THROUGH_UPPER:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
                case FALL_THROUGH_MIDDLE:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
                case BREAK_THROUGH_MIDDLE:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(日)",bollDataEntity.getDay(),bollDataEntity.getIntervalType(),bollDataEntity.getLastIndex()));
                    break;
            }
        }

        return null;
    }

    @Override
    public void processGlobal() {

    }
}
