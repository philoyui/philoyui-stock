package io.philoyui.qmier.qmiermanager.service.indicator.month;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.KdjDataEntity;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.KdjType;
import io.philoyui.qmier.qmiermanager.service.KdjDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class KdjMonthIndicatorProvider implements IndicatorProvider {

    @Autowired
    private KdjDataService kdjDataService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Month));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<KdjDataEntity> kdjDataEntities = kdjDataService.list(searchFilter);

        List<KdjDataEntity> goldenDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.GOLDEN_CROSS).collect(Collectors.toList());
        List<KdjDataEntity> deathDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.DEATH_CROSS).collect(Collectors.toList());
        List<KdjDataEntity> overSellDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.OVER_SELL).collect(Collectors.toList());
        List<KdjDataEntity> overBuyDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.OVER_BUY).collect(Collectors.toList());
        List<KdjDataEntity> bottomTurningDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.BOTTOM_TURNING).collect(Collectors.toList());
        List<KdjDataEntity> topTurningDataList = kdjDataEntities.stream().filter(e -> e.getKdjType() == KdjType.TOP_TURNING).collect(Collectors.toList());

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        if(goldenDataList.size()>1){
            KdjDataEntity kdjDataEntity_0 = goldenDataList.get(0);
            KdjDataEntity kdjDataEntity_1 = goldenDataList.get(1);
            if(kdjDataEntity_0.getjValue() > kdjDataEntity_0.getkValue() && kdjDataEntity_1.getjValue() < kdjDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底背离(月)",kdjDataEntity_0.getDay()));
            }
            if(kdjDataEntity_0.getkValue()<24){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ底部金叉(月)",kdjDataEntity_0.getDay()));
            }
        }

        if(deathDataList.size()>1){
            KdjDataEntity kdjDataEntity_0 = deathDataList.get(0);
            KdjDataEntity kdjDataEntity_1 = deathDataList.get(1);
            if(kdjDataEntity_0.getjValue() < kdjDataEntity_0.getkValue() && kdjDataEntity_1.getjValue() > kdjDataEntity_1.getkValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ顶背离(月)",kdjDataEntity_0.getDay()));
            }
            if(kdjDataEntity_0.getkValue()>76){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ顶部死叉(月)",kdjDataEntity_0.getDay()));
            }
        }

//        if(overSellDataList.size()>1){
//            for (KdjDataEntity kdjDataEntity : overSellDataList) {
//                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ超卖(日)",kdjDataEntity.getDay()));
//            }
//        }
//
//        if(overBuyDataList.size()>1){
//            for (KdjDataEntity kdjDataEntity : overBuyDataList) {
//                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"KDJ超买(日)",kdjDataEntity.getDay()));
//            }
//        }

        if(bottomTurningDataList.size()>1){
            KdjDataEntity kdjDataEntity_0 = bottomTurningDataList.get(0);
            KdjDataEntity kdjDataEntity_1 = bottomTurningDataList.get(1);
            if(kdjDataEntity_0.getkValue() > kdjDataEntity_1.getkValue() && kdjDataEntity_0.getCloseValue() < kdjDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"K线(KDJ)底背离(月)",kdjDataEntity_0.getDay()));
            }
        }

        if(topTurningDataList.size()>1){
            KdjDataEntity kdjDataEntity_0 = topTurningDataList.get(0);
            KdjDataEntity kdjDataEntity_1 = topTurningDataList.get(1);
            if(kdjDataEntity_0.getkValue() < kdjDataEntity_1.getkValue() && kdjDataEntity_0.getCloseValue() > kdjDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"K线(KDJ)顶背离(月)",kdjDataEntity_0.getDay()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "kdj_month";
    }

    @Override
    public void cleanOldData() {
        kdjDataService.deleteMonthData();
        tagStockService.deleteByTagName("KDJ底背离(月)");
        tagStockService.deleteByTagName("KDJ底部金叉(月)");
        tagStockService.deleteByTagName("KDJ顶背离(月)");
        tagStockService.deleteByTagName("KDJ顶部死叉(月)");
        tagStockService.deleteByTagName("KDJ超卖(月)");
        tagStockService.deleteByTagName("KDJ超买(月)");
        tagStockService.deleteByTagName("K线(KDJ)底背离(月");
        tagStockService.deleteByTagName("K线(KDJ)顶背离(月)");

    }

    @Override
    public void processGlobal() {

    }
}
