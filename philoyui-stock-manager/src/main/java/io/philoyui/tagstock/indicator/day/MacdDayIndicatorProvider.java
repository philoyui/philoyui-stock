package io.philoyui.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.entity.indicator.enu.MacdType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.MacdDataEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import io.philoyui.tagstock.service.MacdDataService;
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
            Double upperMacdValue0 = (max.getMacdValue() - 0)/5;
            for (MacdDataEntity goldenData : goldenList) {
                if(goldenData.getDay().getTime() < DateUtils.addDays(new Date(),-5).getTime()){
                    continue;
                }
                if(goldenData.getSignalValue() > 0 && goldenData.getSignalValue() < upperMacdValue0){
                    tagStocks.add(tagStockService.tagStock(stock.getSymbol(),"MACD零轴金叉(日)",goldenData.getDay(),goldenData.getIntervalType(),goldenData.getLastIndex()));
                }
            }
        }

        if(goldenList.size()>1 && goldenList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-14).getTime()){
            MacdDataEntity macdData0 = goldenList.get(0);
            MacdDataEntity macdData1 = goldenList.get(1);
            if(macdData0.getMacdValue() < 0 && macdData1.getMacdValue() < 0 &&
                    macdData0.getMacdValue() > macdData1.getMacdValue() &&
                    macdData0.getCloseValue() < macdData1.getCloseValue() &&
                    macdData0.getDay().getTime() > DateUtils.addWeeks(macdData1.getDay(),2).getTime()
            ){

                tagStocks.add(tagStockService.tagStock(stock.getSymbol(), "MACD底背离(日)", macdData0.getDay(), macdData0.getIntervalType(), macdData0.getLastIndex(), macdData1.getDay()));
            }
        }

        if(deathList.size()>1 && deathList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-14).getTime()){
            MacdDataEntity macdData0 = deathList.get(0);
            MacdDataEntity macdData1 = deathList.get(1);
            if(macdData0.getMacdValue() > 0 && macdData1.getMacdValue() > 0 &&
                    macdData0.getMacdValue() < macdData1.getMacdValue() &&
                    macdData0.getCloseValue() > macdData1.getCloseValue() &&
                    macdData0.getDay().getTime() > DateUtils.addWeeks(macdData1.getDay(),2).getTime()
            ){
                tagStocks.add(tagStockService.tagStock(stock.getSymbol(),"MACD顶背离(日)",macdData0.getDay(),macdData0.getIntervalType(),macdData0.getLastIndex(),macdData1.getDay()));
            }
        }

        return tagStocks;
    }

    @Override
    public void processGlobal() {

    }

}
