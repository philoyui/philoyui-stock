package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.StockEventEntity;

public interface StockEventService extends GenericService<StockEventEntity,Long> {
    void recordEvent(StockEventEntity stockEventEntity);
}
