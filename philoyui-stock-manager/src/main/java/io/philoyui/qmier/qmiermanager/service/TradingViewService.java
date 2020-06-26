package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.TradingViewEntity;

import java.util.List;

public interface TradingViewService extends GenericService<TradingViewEntity,Long> {
    void batchInsert(List<TradingViewEntity> tradingViewEntities);

    void fetchCurrent();
}
