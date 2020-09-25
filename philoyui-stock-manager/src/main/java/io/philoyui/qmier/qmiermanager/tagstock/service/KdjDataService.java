package io.philoyui.qmier.qmiermanager.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.KdjDataEntity;

public interface KdjDataService extends GenericService<KdjDataEntity,Long> {
    void deleteDayData();

    void deleteMonthData();

    void deleteWeekData();
}
