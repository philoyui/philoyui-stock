package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.indicator.CciDataEntity;

public interface CciDataService extends GenericService<CciDataEntity,Long> {
    void deleteDayData();

    void deleteWeekData();

    void deleteMonthData();
}
