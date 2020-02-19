package io.philoyui.qmier.qmiermanager.service.impl;

import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PythonStockFilter implements StockFilter {

    @Autowired
    private TagStockService tagStockService;

    @Override
    public String filterName() {
        return "python";
    }

    @Override
    public Set<String> filterSymbol(StockStrategyEntity stockStrategyEntity) {
        List<TagStockEntity> tagStockEntities = tagStockService.findByTagName(stockStrategyEntity.getName());
        return tagStockEntities.stream().map(TagStockEntity::getSymbol).collect(Collectors.toSet());
    }
}
