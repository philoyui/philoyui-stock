package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.StockIndicatorEntity;

import java.util.List;

public interface StockIndicatorService extends GenericService<StockIndicatorEntity,Long> {

    /**
     * 获取已启用的股票策略
     * @return
     */
    List<StockIndicatorEntity> findDayEnable();

    List<StockIndicatorEntity> findWeekEnable();

    List<StockIndicatorEntity> findMonthEnable();

    List<StockIndicatorEntity> findMin30Enable();

    void executeDayTask();

    void executeWeekTask();

    void executeMonthTask();

    void executeGlobal(Long id);
}
