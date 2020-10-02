package io.philoyui.qmier.qmiermanager.tagstock.indicator.week;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.KdjType;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.KdjDataEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
import io.philoyui.qmier.qmiermanager.tagstock.service.KdjDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

        if(goldenDataList.size()>1){
            KdjDataEntity kdjDataEntity_0 = goldenDataList.get(0);
            KdjDataEntity kdjDataEntity_1 = goldenDataList.get(1);
            if(kdjDataEntity_0.getjValue() > kdjDataEntity_0.getkValue() && kdjDataEntity_1.getjValue() < kdjDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(周)",kdjDataEntity_0.getDay(),kdjDataEntity_0.getIntervalType(),kdjDataEntity_0.getLastIndex()));
            }
            if(kdjDataEntity_0.getkValue()<24){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底部金叉(周)",kdjDataEntity_0.getDay(),kdjDataEntity_0.getIntervalType(),kdjDataEntity_0.getLastIndex()));
            }
        }

        if(deathDataList.size()>1){
            KdjDataEntity kdjDataEntity_0 = deathDataList.get(0);
            KdjDataEntity kdjDataEntity_1 = deathDataList.get(1);
            if(kdjDataEntity_0.getjValue() < kdjDataEntity_0.getkValue() && kdjDataEntity_1.getjValue() > kdjDataEntity_1.getkValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ顶背离(周)",kdjDataEntity_0.getDay(),kdjDataEntity_0.getIntervalType(),kdjDataEntity_0.getLastIndex()));
            }
            if(kdjDataEntity_0.getkValue()>76){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ顶部死叉(周)",kdjDataEntity_0.getDay(),kdjDataEntity_0.getIntervalType(),kdjDataEntity_0.getLastIndex()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "kdj_week";
    }

    @Override
    public void cleanOldData() {
        kdjDataService.deleteWeekData();
        tagStockService.deleteByTagName("KDJ底背离(周)");
        tagStockService.deleteByTagName("KDJ底部金叉(周)");
        tagStockService.deleteByTagName("KDJ顶背离(周)");
        tagStockService.deleteByTagName("KDJ顶部死叉(周)");
        tagStockService.deleteByTagName("KDJ超卖(周)");
        tagStockService.deleteByTagName("KDJ超买(周)");
        tagStockService.deleteByTagName("K线(KDJ)底背离(周)");
        tagStockService.deleteByTagName("K线(KDJ)顶背离(周)");

    }

    @Override
    public void processGlobal() {

    }
}
