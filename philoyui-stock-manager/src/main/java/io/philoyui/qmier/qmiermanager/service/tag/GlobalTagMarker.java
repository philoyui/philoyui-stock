package io.philoyui.qmier.qmiermanager.service.tag;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;

public abstract class GlobalTagMarker extends TagMarker {

    @Override
    public void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String 月) {

    }

    @Override
    public boolean isGlobal() {
        return true;
    }

    @Override
    public void cleanTags() {

    }
}
