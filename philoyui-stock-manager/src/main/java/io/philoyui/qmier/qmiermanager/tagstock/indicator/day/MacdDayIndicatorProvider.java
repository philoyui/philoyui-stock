package io.philoyui.qmier.qmiermanager.tagstock.indicator.day;

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
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MacdDayIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MacdDataService macdDataService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        //获取MACD指标数据，包括金叉死叉，头部，底部
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType",IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<MacdDataEntity> macdDataEntities = macdDataService.list(searchFilter);

        List<MacdDataEntity> goldenDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.GOLDEN_CROSS).collect(Collectors.toList());
        List<MacdDataEntity> deathDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.DEATH_CROSS).collect(Collectors.toList());

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        Optional<MacdDataEntity> max = macdDataEntities.stream().max(Comparator.comparing(MacdDataEntity::getMacdValue));
        Optional<MacdDataEntity> min = macdDataEntities.stream().min(Comparator.comparing(MacdDataEntity::getMacdValue));

        if(max.isPresent()&&min.isPresent()&&max.get().getMacdValue()>0&&min.get().getMacdValue()<0){
            Double upper_macd_value_0 = (max.get().getMacdValue() - 0)/5;
            for (MacdDataEntity goldenData : goldenDataList) {
                if(goldenData.getDay().getTime() < DateUtils.addDays(new Date(),-5).getTime()){
                    continue;
                }
                if(goldenData.getSignalValue() > 0 && goldenData.getSignalValue() < upper_macd_value_0){
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD零轴金叉(日)",goldenData.getDay(),goldenData.getIntervalType(),goldenData.getLastIndex()));
                }
            }
        }

        if(goldenDataList.size()>1 && goldenDataList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-8).getTime()){
            MacdDataEntity macdDataEntity_0 = goldenDataList.get(0);
            MacdDataEntity macdDataEntity_1 = goldenDataList.get(1);
            if(macdDataEntity_0.getMacdValue() < 0 && macdDataEntity_1.getMacdValue() < 0 &&
                    macdDataEntity_0.getMacdValue() > macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() < macdDataEntity_1.getCloseValue() &&
                    macdDataEntity_0.getDay().getTime() > DateUtils.addWeeks(macdDataEntity_1.getDay(),2).getTime()
            ){

                tagStockEntities.add(tagStockService.tagStock(
                        stockEntity.getSymbol(),
                        "MACD底背离(日)",
                        macdDataEntity_0.getDay(),
                        macdDataEntity_0.getIntervalType(),
                        macdDataEntity_0.getLastIndex(),
                        macdDataEntity_1.getDay()
                ));
            }
        }

        if(deathDataList.size()>1 && deathDataList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-8).getTime()){
            MacdDataEntity macdDataEntity_0 = deathDataList.get(0);
            MacdDataEntity macdDataEntity_1 = deathDataList.get(1);
            if(macdDataEntity_0.getMacdValue() > 0 && macdDataEntity_1.getMacdValue() > 0 &&
                    macdDataEntity_0.getMacdValue() < macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() > macdDataEntity_1.getCloseValue() &&
                    macdDataEntity_0.getDay().getTime() > DateUtils.addWeeks(macdDataEntity_1.getDay(),2).getTime()
            ){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD顶背离(日)",macdDataEntity_0.getDay(),macdDataEntity_0.getIntervalType(),macdDataEntity_0.getLastIndex(),macdDataEntity_1.getDay()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public void processGlobal() {

    }

}
