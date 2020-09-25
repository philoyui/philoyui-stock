package io.philoyui.qmier.qmiermanager.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.RsiDataEntity;

public interface RsiDataService extends GenericService<RsiDataEntity,Long> {
    void deleteDayData();

    void deleteMonthData();

    void deleteWeekData();
}
