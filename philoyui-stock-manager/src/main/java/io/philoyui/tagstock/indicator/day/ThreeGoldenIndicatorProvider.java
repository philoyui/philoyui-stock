package io.philoyui.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.entity.enu.MaType;
import io.philoyui.tagstock.entity.MaDataEntity;
import io.philoyui.tagstock.entity.MacdDataEntity;
import io.philoyui.tagstock.entity.VolumeDataEntity;
import io.philoyui.stock.entity.indicator.enu.MacdType;
import io.philoyui.stock.entity.indicator.enu.VolumeType;
import io.philoyui.tagstock.service.MaDataService;
import io.philoyui.tagstock.service.MacdDataService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.service.VolumeDataService;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ThreeGoldenIndicatorProvider implements IndicatorProvider {

    @Autowired
    private MacdDataService macdDataService;

    @Autowired
    private VolumeDataService volumeDataService;

    @Autowired
    private MaDataService maDataService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        return null;
    }

    @Override
    public void processGlobal() {

        SearchFilter macdSearchFilter = SearchFilter.getDefault();
        macdSearchFilter.add(Restrictions.eq("macdType", MacdType.GOLDEN_CROSS));
        macdSearchFilter.add(Restrictions.gte("day", DateUtils.addDays(new Date(),-9)));
        List<MacdDataEntity> macdDataEntities = macdDataService.list(macdSearchFilter);

        Set<String> macdSymbols = macdDataEntities.stream().map(MacdDataEntity::getSymbol).collect(Collectors.toSet());

        SearchFilter maSearchFilter = SearchFilter.getDefault();
        maSearchFilter.add(Restrictions.eq("maType", MaType.Cross_5_10_Golden));
        maSearchFilter.add(Restrictions.gte("day", DateUtils.addDays(new Date(),-9)));
        List<MaDataEntity> maDataEntities = maDataService.list(maSearchFilter);
        Set<String> maSymbols = maDataEntities.stream().map(MaDataEntity::getSymbol).collect(Collectors.toSet());


        SearchFilter volumeSearchFilter = SearchFilter.getDefault();
        volumeSearchFilter.add(Restrictions.eq("volumeType", VolumeType.Cross_5_10_Golden));
        volumeSearchFilter.add(Restrictions.gte("day", DateUtils.addDays(new Date(),-9)));
        List<VolumeDataEntity> volumeDataEntities = volumeDataService.list(volumeSearchFilter);
        Set<String> volumeSymbols = volumeDataEntities.stream().map(VolumeDataEntity::getSymbol).collect(Collectors.toSet());

        macdSymbols.retainAll(maSymbols);
        macdSymbols.retainAll(volumeSymbols);

        for (String macdSymbol : macdSymbols) {
            tagStockService.tagStock(macdSymbol,"三金叉选股", new Date(), IntervalType.Day,-1);
        }

    }
}
