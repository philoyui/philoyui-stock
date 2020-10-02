package io.philoyui.qmier.qmiermanager.tagstock.indicator.minute;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.tagstock.entity.MacdDataEntity;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.MacdType;
import io.philoyui.qmier.qmiermanager.tagstock.service.MacdDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class Macd30IndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MacdDataService macdDataService;

    public List<TagStockEntity> processTags(StockEntity stockEntity) {

        //MACD底背离
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType",IntervalType.Min30));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<MacdDataEntity> macdDataEntities = macdDataService.list(searchFilter);

        List<MacdDataEntity> goldenDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.GOLDEN_CROSS).collect(Collectors.toList());
        List<MacdDataEntity> deathDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.DEATH_CROSS).collect(Collectors.toList());

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        if(goldenDataList.size()>1){
            MacdDataEntity macdDataEntity_0 = goldenDataList.get(0);
            MacdDataEntity macdDataEntity_1 = goldenDataList.get(1);
            if(macdDataEntity_0.getMacdValue() < 0 && macdDataEntity_1.getMacdValue() < 0 &&
                    macdDataEntity_0.getMacdValue() > macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() < macdDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD底背离(30min)",macdDataEntity_0.getDay(),macdDataEntity_0.getIntervalType(),macdDataEntity_0.getLastIndex(),macdDataEntity_1.getDay()));
            }
        }

        if(deathDataList.size()>1){
            MacdDataEntity macdDataEntity_0 = deathDataList.get(0);
            MacdDataEntity macdDataEntity_1 = deathDataList.get(1);
            if(macdDataEntity_0.getMacdValue() > 0 && macdDataEntity_1.getMacdValue() > 0 &&
                    macdDataEntity_0.getMacdValue() < macdDataEntity_1.getMacdValue() &&
                    macdDataEntity_0.getCloseValue() > macdDataEntity_1.getCloseValue()){
                tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MACD顶背离(30min)",macdDataEntity_0.getDay(),macdDataEntity_0.getIntervalType(),macdDataEntity_0.getLastIndex(),macdDataEntity_1.getDay()));
            }
        }

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "macd_day";
    }

    @Transactional
    @Override
    public void cleanOldData() {
        macdDataService.deleteDayData();
        tagStockService.deleteByTagName("DIFF顶背离(日)");
        tagStockService.deleteByTagName("DIFF底背离(日)");
        tagStockService.deleteByTagName("MACD顶背离(日)");
        tagStockService.deleteByTagName("MACD底背离(日)");
        tagStockService.deleteByTagName("MACD零轴死叉(日)");
        tagStockService.deleteByTagName("MACD零轴金叉(日)");
    }

    @Override
    public void processGlobal() {

    }

}
