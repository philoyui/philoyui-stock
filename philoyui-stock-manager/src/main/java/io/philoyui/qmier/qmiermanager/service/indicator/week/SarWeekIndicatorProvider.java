package io.philoyui.qmier.qmiermanager.service.indicator.week;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.SarDataEntity;
import io.philoyui.qmier.qmiermanager.service.SarDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
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

        sarDataService.delete(sarDataEntities);

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "sar_week";
    }

    @Override
    public void cleanOldData() {
        sarDataService.deleteWeekData();
        tagStockService.deleteByTagName("SAR空头止损(周)");
        tagStockService.deleteByTagName("SAR多头止盈(周)");
    }

    @Override
    public void processGlobal() {

    }
}
