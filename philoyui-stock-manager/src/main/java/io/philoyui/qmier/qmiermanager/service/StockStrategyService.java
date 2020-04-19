package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;

import java.util.List;

public interface StockStrategyService extends GenericService<StockStrategyEntity,Long> {

    void tagStock(StockStrategyEntity stockStrategyEntity);

    void tagStock(String strategyIdentifier);

    void enable(long id);

    void disable(long id);

    void processWithMonthTimer();

    void processWithDayTimer();

    void processWithWeekTimer();
}
