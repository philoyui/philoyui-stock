package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.service.indicator.StockIndicator;

import java.util.List;

public interface IndicatorService {

    List<StockIndicator> findAllEnable();

}
