package io.philoyui.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.entity.indicator.enu.CciType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.CciDataEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import io.philoyui.tagstock.service.CciDataService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CciIndicatorProvider implements IndicatorProvider {

    @Autowired
    private CciDataService cciDataService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<CciDataEntity> cciDataEntities = cciDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        List<CciDataEntity> topDataList = cciDataEntities.stream().filter(e -> e.getCciType() == CciType.TOP).collect(Collectors.toList());

        List<CciDataEntity> bottomDataList = cciDataEntities.stream().filter(e -> e.getCciType() == CciType.BOTTOM).collect(Collectors.toList());


        if(topDataList.size()>1&& topDataList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-14).getTime()) {
            CciDataEntity oldCciDataEntity = topDataList.get(0);
            CciDataEntity newCciDataEntity = topDataList.get(1);
            if(newCciDataEntity.getCciValue() < oldCciDataEntity.getCciValue() && newCciDataEntity.getCloseValue() > oldCciDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI顶背离(日)",newCciDataEntity.getDay(),newCciDataEntity.getIntervalType(),newCciDataEntity.getLastIndex(),oldCciDataEntity.getDay()));
            }
        }

        if(bottomDataList.size()>1&& bottomDataList.get(0).getDay().getTime() > DateUtils.addDays(new Date(),-14).getTime()) {
            CciDataEntity oldCciDataEntity = bottomDataList.get(0);
            CciDataEntity newCciDataEntity = bottomDataList.get(1);
            if(newCciDataEntity.getCciValue() > oldCciDataEntity.getCciValue() && newCciDataEntity.getCloseValue() < oldCciDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI底背离(日)",newCciDataEntity.getDay(),newCciDataEntity.getIntervalType(),newCciDataEntity.getLastIndex(),oldCciDataEntity.getDay()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public void processGlobal() {

    }
}
