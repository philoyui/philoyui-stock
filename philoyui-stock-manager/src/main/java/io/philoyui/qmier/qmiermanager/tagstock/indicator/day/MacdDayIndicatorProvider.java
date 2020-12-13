package io.philoyui.qmier.qmiermanager.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.MacdType;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.MacdDataEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
import io.philoyui.qmier.qmiermanager.tagstock.service.MacdDataService;
import org.apache.commons.lang3.time.DateUtils;
import org.checkerframework.checker.units.qual.min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MacdDayIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MacdDataService macdDataService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stock) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType",IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stock.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<MacdDataEntity> macdList = macdDataService.list(searchFilter);

        List<MacdDataEntity> goldenList = macdList.stream().filter(e -> e.getMacdType() == MacdType.GOLDEN_CROSS).collect(Collectors.toList());
        List<MacdDataEntity> deathList = macdList.stream().filter(e -> e.getMacdType() == MacdType.DEATH_CROSS).collect(Collectors.toList());

        MacdDataEntity max = macdList.stream().max(Comparator.comparing(MacdDataEntity::getMacdValue)).orElse(new MacdDataEntity());

        MacdDataEntity min = macdList.stream().min(Comparator.comparing(MacdDataEntity::getMacdValue)).orElse(new MacdDataEntity());

        List<TagStockEntity> tagStocks = new ArrayList<>();

        if(max.getMacdValue() > 0 && min.getMacdValue() < 0){
            Double upper_macd_value_0 = (max.getMacdValue() - 0)/5;
            for (MacdDataEntity goldenData : goldenList) {
                if(goldenData.getDay().getTime() < DateUtils.addDays(new Date(),-5).getTime()){
                    continue;
                }
                if(goldenData.getSignalValue() > 0 && goldenData.getSignalValue() < upper_macd_value_0){
                    tagStocks.add(tagStockService.tagStock(stock.getSymbol(),"MACD零轴金叉(日)",goldenData.getDay(),goldenData.getIntervalType(),goldenData.getLastIndex()));
                }
            }
        }

        if(goldenList.size()>1 && goldenList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-8).getTime()){
            MacdDataEntity macdData_0 = goldenList.get(0);
            MacdDataEntity macdData_1 = goldenList.get(1);
            if(macdData_0.getMacdValue() < 0 && macdData_1.getMacdValue() < 0 &&
                    macdData_0.getMacdValue() > macdData_1.getMacdValue() &&
                    macdData_0.getCloseValue() < macdData_1.getCloseValue() &&
                    macdData_0.getDay().getTime() > DateUtils.addWeeks(macdData_1.getDay(),2).getTime()
            ){

                tagStocks.add(tagStockService.tagStock(stock.getSymbol(), "MACD底背离(日)", macdData_0.getDay(), macdData_0.getIntervalType(), macdData_0.getLastIndex(), macdData_1.getDay()));
            }
        }

        if(deathList.size()>1 && deathList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-8).getTime()){
            MacdDataEntity macdData_0 = deathList.get(0);
            MacdDataEntity macdData_1 = deathList.get(1);
            if(macdData_0.getMacdValue() > 0 && macdData_1.getMacdValue() > 0 &&
                    macdData_0.getMacdValue() < macdData_1.getMacdValue() &&
                    macdData_0.getCloseValue() > macdData_1.getCloseValue() &&
                    macdData_0.getDay().getTime() > DateUtils.addWeeks(macdData_1.getDay(),2).getTime()
            ){
                tagStocks.add(tagStockService.tagStock(stock.getSymbol(),"MACD顶背离(日)",macdData_0.getDay(),macdData_0.getIntervalType(),macdData_0.getLastIndex(),macdData_1.getDay()));
            }
        }

        return tagStocks;
    }

    @Override
    public void processGlobal() {

    }

}
