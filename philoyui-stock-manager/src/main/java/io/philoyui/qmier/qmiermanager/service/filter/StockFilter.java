package io.philoyui.qmier.qmiermanager.service.filter;

import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;

import java.util.Set;

public interface StockFilter {

    String filterName();

    Set<String> filterSymbol(StockStrategyEntity stockStrategyEntity);

}
