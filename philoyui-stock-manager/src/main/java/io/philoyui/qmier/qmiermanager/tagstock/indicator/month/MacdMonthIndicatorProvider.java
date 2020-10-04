package io.philoyui.qmier.qmiermanager.tagstock.indicator.month;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.MacdType;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.MacdDataEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
import io.philoyui.qmier.qmiermanager.tagstock.service.MacdDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MacdMonthIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MacdDataService macdDataService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType",IntervalType.Month));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<MacdDataEntity> macdDataEntities = macdDataService.list(searchFilter);

        List<MacdDataEntity> goldenDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.GOLDEN_CROSS).collect(Collectors.toList());
        List<MacdDataEntity> deathDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.DEATH_CROSS).collect(Collectors.toList());
        List<MacdDataEntity> bottomDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.BOTTOM_DIFF).collect(Collectors.toList());
        List<MacdDataEntity> topDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.TOP_DIFF).collect(Collectors.toList());

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        Optional<MacdDataEntity> max = macdDataEntities.stream().max(Comparator.comparing(MacdDataEntity::getMacdValue));
        Optional<MacdDataEntity> min = macdDataEntities.stream().min(Comparator.comparing(MacdDataEntity::getMacdValue));

        if(max.isPresent()&&min.isPresent()&&max.get().getMacdValue()>0&&min.get().getMacdValue()<0){
            Double upper_macd_value_0 = (max.get().getMacdValue() - 0)/5;
            Double lower_macd_value_0 = 0 - (0-min.get().getMacdValue())/5;
            for (MacdDataEntity goldenData : goldenDataList) {
                if(goldenData.getSignalValue() > 0 && goldenData.getSignalValue() < upper_macd_value_0){
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD零轴金叉(月)",goldenData.getDay(),goldenData.getIntervalType(),goldenData.getLastIndex()));
                }
            }

            for (MacdDataEntity deathData : deathDataList) {
                if(deathData.getSignalValue() < 0 && deathData.getSignalValue() > lower_macd_value_0){
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD零轴死叉(月)",deathData.getDay(),deathData.getIntervalType(),deathData.getLastIndex()));
                }
            }
        }

        if(goldenDataList.size()>1){
            MacdDataEntity macdDataEntity_0 = goldenDataList.get(0);
            MacdDataEntity macdDataEntity_1 = goldenDataList.get(1);
            if(macdDataEntity_0.getMacdValue() < 0 && macdDataEntity_1.getMacdValue() < 0 &&
                    macdDataEntity_0.getMacdValue() > macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() < macdDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD底背离(月)",macdDataEntity_0.getDay(),macdDataEntity_0.getIntervalType(),macdDataEntity_0.getLastIndex(),macdDataEntity_1.getDay()));
            }
        }

        if(deathDataList.size()>1){
            MacdDataEntity macdDataEntity_0 = deathDataList.get(0);
            MacdDataEntity macdDataEntity_1 = deathDataList.get(1);
            if(macdDataEntity_0.getMacdValue() > 0 && macdDataEntity_1.getMacdValue() > 0 &&
                    macdDataEntity_0.getMacdValue() < macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() > macdDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD顶背离(月)",macdDataEntity_0.getDay(),macdDataEntity_0.getIntervalType(),macdDataEntity_0.getLastIndex(),macdDataEntity_1.getDay()));
            }
        }

        if(bottomDataList.size()>1){
            MacdDataEntity macdDataEntity_0 = bottomDataList.get(0);
            MacdDataEntity macdDataEntity_1 = bottomDataList.get(1);
            if(macdDataEntity_0.getMacdValue() < 0 && macdDataEntity_1.getMacdValue() < 0 &&
                    macdDataEntity_0.getMacdValue() > macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() < macdDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"DIFF底背离(月)",macdDataEntity_0.getDay(),macdDataEntity_0.getIntervalType(),macdDataEntity_0.getLastIndex(),macdDataEntity_1.getDay()));
            }
        }

        if(topDataList.size()>1){
            MacdDataEntity macdDataEntity_0 = topDataList.get(0);
            MacdDataEntity macdDataEntity_1 = topDataList.get(1);
            if(macdDataEntity_0.getMacdValue() > 0 && macdDataEntity_1.getMacdValue() > 0 &&
                    macdDataEntity_0.getMacdValue() < macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() > macdDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"DIFF顶背离(月)",macdDataEntity_0.getDay(),macdDataEntity_0.getIntervalType(),macdDataEntity_0.getLastIndex(),macdDataEntity_1.getDay()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public void processGlobal() {

    }
}
