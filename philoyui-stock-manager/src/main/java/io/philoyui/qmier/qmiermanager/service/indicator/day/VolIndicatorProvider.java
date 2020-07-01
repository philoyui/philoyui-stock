package io.philoyui.qmier.qmiermanager.service.indicator.day;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.dao.VolumeDataDao;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.MaDataEntity;
import io.philoyui.qmier.qmiermanager.entity.indicator.VolumeDataEntity;
import io.philoyui.qmier.qmiermanager.service.DayDataService;
import io.philoyui.qmier.qmiermanager.service.MaDataService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.VolumeDataService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class VolIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private VolumeDataService volumeDataService;

    @Autowired
    private DayDataService dayDataService;

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

        SearchFilter searchFilter1 = SearchFilter.getPagedSearchFilter(0,22);
        searchFilter1.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        searchFilter1.add(Order.desc("day"));
        List<DataDayEntity> dataDayEntities = dayDataService.paged(searchFilter).getContent();

        Long maxVolume = dataDayEntities.stream().max(Comparator.comparing(DataDayEntity::getVolume)).get().getVolume();
        DataDayEntity dataDayEntity1 = dataDayEntities.get(0);
        DataDayEntity dataDayEntity2 = dataDayEntities.get(1);
        DataDayEntity dataDayEntity3 = dataDayEntities.get(2);

        if(dataDayEntity1!=null && dataDayEntity1.getVolume() < maxVolume/5){
            tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"地量",dataDayEntity1.getDay(),IntervalType.Day,-1));
        }

        if(dataDayEntity2!=null && dataDayEntity2.getVolume() < maxVolume/5){
            tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"地量",dataDayEntity2.getDay(),IntervalType.Day,-2));
        }

        if(dataDayEntity3!=null && dataDayEntity3.getVolume() < maxVolume/5){
            tagStockEntities.add(tagStockService.tagStock(stockEntity.getSymbol(),"地量",dataDayEntity3.getDay(),IntervalType.Day,-3));
        }

        return tagStockEntities;
    }

    @Override
    public String identifier() {
        return "vol_day";
    }

    @Override
    public void cleanOldData() {
        volumeDataService.deleteDayData();
        tagStockService.deleteByTagName("量线5穿10金叉");
        tagStockService.deleteByTagName("量线5穿20金叉");
        tagStockService.deleteByTagName("量线5穿10死叉");
        tagStockService.deleteByTagName("量线5穿20死叉");
        tagStockService.deleteByTagName("地量");
    }

    @Override
    public void processGlobal() {

    }

}
