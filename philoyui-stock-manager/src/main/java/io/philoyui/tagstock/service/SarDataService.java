package io.philoyui.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.tagstock.entity.SarDataEntity;

public interface SarDataService extends GenericService<SarDataEntity,Long> {
    void deleteDayData();

    void deleteWeekData();

    void deleteMonthData();

    String findCurrent(String symbol);
}
