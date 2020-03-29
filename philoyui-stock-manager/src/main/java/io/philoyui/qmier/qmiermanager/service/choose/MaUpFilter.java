package io.philoyui.qmier.qmiermanager.service.choose;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.tictactec.ta.lib.Core;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import io.philoyui.qmier.qmiermanager.utils.TalibUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 10日线，20日线，30日线多头排列
 */
@Component
public class MaUpFilter implements StockFilter {

    @Autowired
    private DataDayService dataDayService;

    @Autowired
    private StockService stockService;

    private Core core;

    @Override
    public String filterName() {
        return "ma_up";
    }

    @PostConstruct
    public void start(){
        core = new Core();
    }

    @Override
    public Set<String> filterSymbol(StockStrategyEntity stockStrategyEntity) {

        SearchFilter searchFilter = SearchFilter.getDefault();
        List<StockEntity> stockEntities = stockService.list(searchFilter);

        Set<String> symbolSet = new HashSet<>();

        for (int i = 0; i < stockEntities.size(); i++) {
            StockEntity stockEntity = stockEntities.get(i);
            double[] closeArray = dataDayService.findCloseData(stockEntity);
            double[] ma10Array = TalibUtils.ma(closeArray, 10);
            double[] ma20Array = TalibUtils.ma(closeArray, 20);
            double[] ma30Array = TalibUtils.ma(closeArray, 30);

            if ( ma10Array[0] > ma10Array[1] && ma20Array[0] > ma20Array[1] && ma30Array[0] > ma30Array[1]) {
                symbolSet.add(stockEntity.getSymbol());
            }
        }

        return symbolSet;
    }
}
