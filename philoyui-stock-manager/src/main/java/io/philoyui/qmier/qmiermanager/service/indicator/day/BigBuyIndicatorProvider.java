package io.philoyui.qmier.qmiermanager.service.indicator.day;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;

import java.util.List;

public class BigBuyIndicatorProvider implements IndicatorProvider {

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        return null;
    }

    @Override
    public String identifier() {
        return null;
    }

    @Override
    public void cleanOldData() {

    }

}
