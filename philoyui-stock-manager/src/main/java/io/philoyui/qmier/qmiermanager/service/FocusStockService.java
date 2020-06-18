package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.FocusStockEntity;

public interface FocusStockService extends GenericService<FocusStockEntity,Long> {
    FocusStockEntity findBySymbol(String symbol);
}
