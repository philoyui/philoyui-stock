package io.philoyui.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.tagstock.entity.CciDataEntity;

public interface CciDataService extends GenericService<CciDataEntity,Long> {
    void deleteDayData();

    void deleteWeekData();

    void deleteMonthData();
}
