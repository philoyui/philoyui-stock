package io.philoyui.qmier.qmiermanager.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.tagstock.entity.WrDataEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.service.WrDataService;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WrIndicatorProvider implements IndicatorProvider {

    @Autowired
    private WrDataService wrDataService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<WrDataEntity> wrDataEntities = wrDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        for (WrDataEntity wrDataEntity : wrDataEntities) {
            switch (wrDataEntity.getWrType()){
                case Buy_Point_20:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"WR多头(日)",wrDataEntity.getDay(),wrDataEntity.getIntervalType(),wrDataEntity.getLastIndex()));
                    break;
                case Sell_Point_20:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"WR空头(日)",wrDataEntity.getDay(),wrDataEntity.getIntervalType(),wrDataEntity.getLastIndex()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public void processGlobal() {

    }
}