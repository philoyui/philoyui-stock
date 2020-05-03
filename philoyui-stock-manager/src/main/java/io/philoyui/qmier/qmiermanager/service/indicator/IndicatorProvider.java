package io.philoyui.qmier.qmiermanager.service.indicator;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;

import java.util.List;

public interface IndicatorProvider {

    List<TagStockEntity> processTags(StockEntity stockEntity);

    String identifier();

    void cleanOldData(String symbol);

}
