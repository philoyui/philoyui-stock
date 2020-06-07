package io.philoyui.qmier.qmiermanager.service.indicator.month;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.CciDataEntity;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.CciType;
import io.philoyui.qmier.qmiermanager.service.CciDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CciMonthIndicatorProvider implements IndicatorProvider {

    @Autowired
    private CciDataService cciDataService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Month));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<CciDataEntity> cciDataEntities = cciDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        for (CciDataEntity cciDataEntity : cciDataEntities) {
            switch (cciDataEntity.getCciType()){
                case BREAK_100:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI进入强势(月)",cciDataEntity.getDay(), cciDataEntity.getIntervalType(), cciDataEntity.getLastIndex()));
                    break;
                case BREAK_NEGATIVE_100:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI多头开启(月)",cciDataEntity.getDay(), cciDataEntity.getIntervalType(), cciDataEntity.getLastIndex()));
                    break;
                case FALL_100:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI空头开启(月)",cciDataEntity.getDay(), cciDataEntity.getIntervalType(), cciDataEntity.getLastIndex()));
                    break;
                case FALL_NEGATIVE_100:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI进入弱势(月)",cciDataEntity.getDay(), cciDataEntity.getIntervalType(), cciDataEntity.getLastIndex()));
                    break;
            }
        }

        List<CciDataEntity> topDataList = cciDataEntities.stream().filter(e -> e.getCciType() == CciType.TOP).collect(Collectors.toList());

        List<CciDataEntity> bottomDataList = cciDataEntities.stream().filter(e -> e.getCciType() == CciType.BOTTOM).collect(Collectors.toList());

        for (int i = 0; i < topDataList.size()-1; i++) {
            CciDataEntity oldCciDataEntity = topDataList.get(i);
            CciDataEntity newCciDataEntity = topDataList.get(i+1);
            if(newCciDataEntity.getCciValue() < oldCciDataEntity.getCciValue() && newCciDataEntity.getCloseValue() > oldCciDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI顶背离(月)",newCciDataEntity.getDay(),newCciDataEntity.getIntervalType(),newCciDataEntity.getLastIndex()));
            }
        }

        for (int i = 0; i < bottomDataList.size()-1; i++) {
            CciDataEntity oldCciDataEntity = bottomDataList.get(i);
            CciDataEntity newCciDataEntity = bottomDataList.get(i+1);
            if(newCciDataEntity.getCciValue() > oldCciDataEntity.getCciValue() && newCciDataEntity.getCloseValue() < oldCciDataEntity.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"CCI底背离(月)",newCciDataEntity.getDay(),newCciDataEntity.getIntervalType(),newCciDataEntity.getLastIndex()));
            }
        }

        cciDataService.delete(cciDataEntities);

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "cci_month";
    }

    @Override
    public void cleanOldData() {
        cciDataService.deleteMonthData();
        tagStockService.deleteByTagName("CCI进入强势(月)");
        tagStockService.deleteByTagName("CCI多头开启(月)");
        tagStockService.deleteByTagName("CCI空头开启(月)");
        tagStockService.deleteByTagName("CCI进入弱势(月)");
        tagStockService.deleteByTagName("CCI结束强势(月)");
        tagStockService.deleteByTagName("CCI结束弱势(月)");
        tagStockService.deleteByTagName("CCI顶背离(月)");
        tagStockService.deleteByTagName("CCI底背离(月)");

    }

    @Override
    public void processGlobal() {

    }
}
