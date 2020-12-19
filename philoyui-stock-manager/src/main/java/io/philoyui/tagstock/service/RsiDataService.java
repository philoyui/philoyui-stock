package io.philoyui.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.tagstock.entity.RsiDataEntity;

public interface RsiDataService extends GenericService<RsiDataEntity,Long> {
    void deleteDayData();

    void deleteMonthData();

    void deleteWeekData();
}
