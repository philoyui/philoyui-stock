package io.philoyui.qmier.qmiermanager.service.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.MaDataEntity;
import io.philoyui.qmier.qmiermanager.service.MaDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MaIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MaDataService maDataService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        //MACD底背离
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<MaDataEntity> maDataEntities = maDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        for (MaDataEntity maDataEntity : maDataEntities) {
            switch (maDataEntity.getMaType()){
                case UpTrend:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MA多头排列",maDataEntity.getDay(),IntervalType.Day,maDataEntity.getLastIndex()));
                case DownTrend:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"MA空头排列",maDataEntity.getDay(),IntervalType.Day,maDataEntity.getLastIndex()));
            }

        }

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "ma_day";
    }

    @Override
    public void cleanOldData() {
        maDataService.deleteDayData();
        tagStockService.deleteByTagName("MA多头排列");
        tagStockService.deleteByTagName("MA空头排列");
    }

    @Override
    public void processGlobal() {

    }
}
