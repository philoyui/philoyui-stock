package io.philoyui.tagstock.indicator.week;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.entity.indicator.enu.KdjType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.KdjDataEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import io.philoyui.tagstock.service.KdjDataService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KdjWeekIndicatorProvider implements IndicatorProvider {

    @Autowired
    private KdjDataService kdjDataService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Week));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<KdjDataEntity> kdjDataEntities = kdjDataService.list(searchFilter);

        List<KdjDataEntity> goldenDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.GOLDEN_CROSS).collect(Collectors.toList());
        List<KdjDataEntity> deathDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.DEATH_CROSS).collect(Collectors.toList());

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        if(goldenDataList.size()>1 && goldenDataList.get(0).getDay().getTime() > DateUtils.addWeeks(new Date(),-10).getTime()){
            KdjDataEntity kdjDataEntity_0 = goldenDataList.get(0);
            if(kdjDataEntity_0.getkValue()<24){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底部金叉(周)",kdjDataEntity_0.getDay(),kdjDataEntity_0.getIntervalType(),kdjDataEntity_0.getLastIndex()));
            }
        }

        if(deathDataList.size()>1 && deathDataList.get(0).getDay().getTime() > DateUtils.addWeeks(new Date(),-10).getTime()){
            KdjDataEntity kdjDataEntity_0 = deathDataList.get(0);
            if(kdjDataEntity_0.getkValue()>76){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ顶部死叉(周)",kdjDataEntity_0.getDay(),kdjDataEntity_0.getIntervalType(),kdjDataEntity_0.getLastIndex()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public void processGlobal() {

    }
}
