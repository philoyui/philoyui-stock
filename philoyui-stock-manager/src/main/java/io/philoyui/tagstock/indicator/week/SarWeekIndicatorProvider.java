package io.philoyui.tagstock.indicator.week;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.SarDataEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import io.philoyui.tagstock.service.SarDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SarWeekIndicatorProvider implements IndicatorProvider {

    @Autowired
    private SarDataService sarDataService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Week));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<SarDataEntity> sarDataEntities = sarDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        for (SarDataEntity sarDataEntity : sarDataEntities) {
            switch (sarDataEntity.getSarType()){
                case Buy:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"SAR空头止损(周)",sarDataEntity.getDay(),sarDataEntity.getIntervalType(),sarDataEntity.getLastIndex()));
                    break;
                case Sell:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"SAR多头止盈(周)",sarDataEntity.getDay(),sarDataEntity.getIntervalType(),sarDataEntity.getLastIndex()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public void processGlobal() {

    }
}
