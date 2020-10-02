package io.philoyui.qmier.qmiermanager.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.RsiType;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.RsiDataEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
import io.philoyui.qmier.qmiermanager.tagstock.service.RsiDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RsiIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private RsiDataService rsiDataService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<RsiDataEntity> sarDataEntities = rsiDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        List<RsiDataEntity> topDataList = sarDataEntities.stream().filter(e -> e.getRsiType() == RsiType.TOP).collect(Collectors.toList());

        List<RsiDataEntity> bottomDataList = sarDataEntities.stream().filter(e -> e.getRsiType() == RsiType.BOTTOM).collect(Collectors.toList());

        if(topDataList.size()>1) {
            RsiDataEntity oldRsiDataEntity = topDataList.get(0);
            RsiDataEntity newRsiDataEntity = topDataList.get(1);
            if(newRsiDataEntity.getRsiValue() < oldRsiDataEntity.getRsiValue() && newRsiDataEntity.getCloseValue() > oldRsiDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"RSI顶背离(日)",newRsiDataEntity.getDay(),newRsiDataEntity.getIntervalType(),newRsiDataEntity.getLastIndex(),oldRsiDataEntity.getDay()));
            }
        }

        if(bottomDataList.size()>1) {
            RsiDataEntity oldRsiDataEntity = bottomDataList.get(0);
            RsiDataEntity newRsiDataEntity = bottomDataList.get(1);
            if(newRsiDataEntity.getRsiValue() > oldRsiDataEntity.getRsiValue() && newRsiDataEntity.getCloseValue() < oldRsiDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"RSI底背离(日)",newRsiDataEntity.getDay(),newRsiDataEntity.getIntervalType(),newRsiDataEntity.getLastIndex(),oldRsiDataEntity.getDay()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "rsi_day";
    }

    @Override
    public void cleanOldData() {
        rsiDataService.deleteDayData();
        tagStockService.deleteByTagName("RSI多头(日)");
        tagStockService.deleteByTagName("RSI空头(日)");
        tagStockService.deleteByTagName("RSI顶背离(日)");
        tagStockService.deleteByTagName("RSI底背离(日)");
    }

    @Override
    public void processGlobal() {

    }
}
