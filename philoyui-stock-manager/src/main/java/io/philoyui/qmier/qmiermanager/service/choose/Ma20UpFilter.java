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
public class Ma20UpFilter implements StockFilter {

    @Autowired
    private DataDayService dataDayService;

    @Autowired
    private StockService stockService;

    @Override
    public String filterName() {
        return "20ma";
    }

    @Override
    public Set<String> filterSymbol(StockStrategyEntity stockStrategyEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        List<StockEntity> stockEntities = stockService.list(searchFilter);

        Set<String> symbolSet = new HashSet<>();

        for (int i = 0; i < stockEntities.size(); i++) {
            StockEntity stockEntity = stockEntities.get(i);
            double[] closeArray = dataDayService.findCloseData(stockEntity);
            double[] ma20Array = TalibUtils.ma(closeArray, 20);
            if ( ma20Array[0] > ma20Array[1] && ma20Array[1] < ma20Array[2]) {
                symbolSet.add(stockEntity.getSymbol());
            }
        }
        return symbolSet;
    }
}
