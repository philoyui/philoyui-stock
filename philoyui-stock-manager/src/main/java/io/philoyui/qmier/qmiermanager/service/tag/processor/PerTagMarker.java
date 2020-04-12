package io.philoyui.qmier.qmiermanager.service.tag.processor;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.tag.GlobalTagMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PerTagMarker extends GlobalTagMarker {

    @Autowired
    private StockService stockService;

    @Override
    public void processGlobal() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lte("per",20));
        searchFilter.add(Restrictions.gte("per",0));
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        List<String> symbolList = stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toList());
        this.tagStocks(symbolList,"低估值");
    }


}
