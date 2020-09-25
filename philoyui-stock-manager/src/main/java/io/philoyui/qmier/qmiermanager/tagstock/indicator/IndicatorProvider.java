package io.philoyui.qmier.qmiermanager.tagstock.indicator;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;

import java.util.List;

public interface IndicatorProvider {

    List<TagStockEntity> processTags(StockEntity stockEntity);

    String identifier();

    void cleanOldData();

    void processGlobal();
}
