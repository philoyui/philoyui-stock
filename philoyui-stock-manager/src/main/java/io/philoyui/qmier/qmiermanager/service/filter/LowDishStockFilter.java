package io.philoyui.qmier.qmiermanager.service.filter;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LowDishStockFilter implements StockFilter{

    @Autowired
    private StockService stockService;

    @Override
    public Set<String> filterSymbol(StockStrategyEntity stockStrategyEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lt("totalPrice",400000));
        List<StockEntity> stockEntities = stockService.list(searchFilter);
        return stockEntities.stream().map(StockEntity::getSymbol).collect(Collectors.toSet());
    }

    @Override
    public String filterName() {
        return "little_dish_stock";
    }
}
