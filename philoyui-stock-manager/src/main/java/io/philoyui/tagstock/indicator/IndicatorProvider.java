package io.philoyui.tagstock.indicator;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.tagstock.entity.TagStockEntity;

import java.util.List;

public interface IndicatorProvider {

    List<TagStockEntity> processTags(StockEntity stockEntity);

    void processGlobal();
}
