package io.philoyui.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.entity.VolumeDataEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import io.philoyui.tagstock.service.VolumeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VolIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private VolumeDataService volumeDataService;


    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        //MACD底背离
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter.add(Order.desc("day"));
        List<VolumeDataEntity> volumeDataEntities = volumeDataService.list(searchFilter);

        List<TagStockEntity> tagStockEntities = new ArrayList<>();

        for (VolumeDataEntity volumeDataEntity : volumeDataEntities) {
            switch (volumeDataEntity.getVolumeType()){
                case Cross_5_10_Golden:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"量线5穿10金叉",volumeDataEntity.getDay(),IntervalType.Day,volumeDataEntity.getLastIndex()));
                    break;

                case Cross_5_20_Golden:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"量线5穿20金叉",volumeDataEntity.getDay(),IntervalType.Day,volumeDataEntity.getLastIndex()));
                    break;

                case Cross_5_10_Death:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"量线5穿10死叉",volumeDataEntity.getDay(),IntervalType.Day,volumeDataEntity.getLastIndex()));
                    break;

                case Cross_5_20_Death:
                    tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"量线5穿20死叉",volumeDataEntity.getDay(),IntervalType.Day,volumeDataEntity.getLastIndex()));
                    break;

            }

        }

        return tagStockEntities;
    }

    @Override
    public void processGlobal() {

    }

}
