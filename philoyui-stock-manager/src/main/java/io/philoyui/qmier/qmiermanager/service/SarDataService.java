package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.indicator.SarDataEntity;

public interface SarDataService extends GenericService<SarDataEntity,Long> {
    void deleteDayData();

    void deleteWeekData();

    void deleteMonthData();
}
