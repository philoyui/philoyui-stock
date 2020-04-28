package io.philoyui.qmier.qmiermanager.service.tag;

import io.philoyui.qmier.qmiermanager.domain.StockHistoryData;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;

public abstract class GlobalTagMarker extends TagMarker {

    @Override
    public void processEachStock(StockHistoryData stockHistoryData, StockEntity stockEntity, String prefix) {

    }

    @Override
    public boolean isGlobal() {
        return true;
    }

    @Override
    public void cleanTags(String prefix) {

    }

}
