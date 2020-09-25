package io.philoyui.qmier.qmiermanager.tagstock.indicator.week;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.tagstock.entity.RsiDataEntity;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.RsiType;
import io.philoyui.qmier.qmiermanager.tagstock.service.RsiDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RsiWeekIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private RsiDataService rsiDataService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Week));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<RsiDataEntity> sarDataEntities = rsiDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        for (RsiDataEntity rsiDataEntity : sarDataEntities) {
            switch (rsiDataEntity.getRsiType()){
                case BREAK_30:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"RSI多头(周)",rsiDataEntity.getDay(),rsiDataEntity.getIntervalType(),rsiDataEntity.getLastIndex()));
                    break;
                case FALL_70:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"RSI空头(周)",rsiDataEntity.getDay(),rsiDataEntity.getIntervalType(),rsiDataEntity.getLastIndex()));
            }
        }

        List<RsiDataEntity> topDataList = sarDataEntities.stream().filter(e -> e.getRsiType() == RsiType.TOP).collect(Collectors.toList());

        List<RsiDataEntity> bottomDataList = sarDataEntities.stream().filter(e -> e.getRsiType() == RsiType.BOTTOM).collect(Collectors.toList());

        for (int i = 0; i < topDataList.size()-1; i++) {
            RsiDataEntity oldRsiDataEntity = topDataList.get(i);
            RsiDataEntity newRsiDataEntity = topDataList.get(i+1);
            if(newRsiDataEntity.getRsiValue() < oldRsiDataEntity.getRsiValue() && newRsiDataEntity.getCloseValue() > oldRsiDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"RSI顶背离(周)",newRsiDataEntity.getDay(),newRsiDataEntity.getIntervalType(),newRsiDataEntity.getLastIndex()));
            }
        }

        for (int i = 0; i < bottomDataList.size()-1; i++) {
            RsiDataEntity oldRsiDataEntity = bottomDataList.get(i);
            RsiDataEntity newRsiDataEntity = bottomDataList.get(i+1);
            if(newRsiDataEntity.getRsiValue() > oldRsiDataEntity.getRsiValue() && newRsiDataEntity.getCloseValue() < oldRsiDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"RSI底背离(周)",newRsiDataEntity.getDay(),newRsiDataEntity.getIntervalType(),newRsiDataEntity.getLastIndex()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "rsi_week";
    }

    @Override
    public void cleanOldData() {
        rsiDataService.deleteWeekData();
        tagStockService.deleteByTagName("RSI多头(周)");
        tagStockService.deleteByTagName("RSI空头(周)");
        tagStockService.deleteByTagName("RSI顶背离(周)");
        tagStockService.deleteByTagName("RSI底背离(周)");
    }

    @Override
    public void processGlobal() {

    }
}
