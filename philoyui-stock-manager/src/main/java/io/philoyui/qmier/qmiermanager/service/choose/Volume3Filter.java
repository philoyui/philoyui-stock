package io.philoyui.qmier.qmiermanager.service.choose;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Volume3Filter implements StockFilter {

    @Autowired
    private DataDayService dataDayService;

    @Autowired
    private StockService stockService;

    @Override
    public String filterName() {
        return "3_volume";
    }

    @Override
    public Set<String> filterSymbol(StockStrategyEntity stockStrategyEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        List<StockEntity> stockEntities = stockService.list(searchFilter);

        Set<String> symbolSet = new HashSet<>();

        for (int i = 0; i < stockEntities.size(); i++) {
            StockEntity stockEntity = stockEntities.get(i);
            double[] volumeArray = dataDayService.findVolumeData(stockEntity);
            double[] closeArray = dataDayService.findCloseData(stockEntity);
            double[] openArray = dataDayService.findOpenData(stockEntity);
            if ( volumeArray[0] > volumeArray[1] * 3 && ((closeArray[0] - openArray[0])/openArray[0]<0.8 || (closeArray[0] - openArray[0])/openArray[0]> -0.8)){
                symbolSet.add(stockEntity.getSymbol());
            }
        }
        return symbolSet;
    }
}
