package io.philoyui.qmier.qmiermanager.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.tagstock.entity.BollDataEntity;
import io.philoyui.qmier.qmiermanager.tagstock.service.BollDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
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
    public String identifier() {
        return "boll_day";
    }

    @Override
    public void cleanOldData() {

    }

    @Override
    public void processGlobal() {

    }
}
