package io.philoyui.qmier.qmiermanager.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.CciDataEntity;

public interface CciDataService extends GenericService<CciDataEntity,Long> {
    void deleteDayData();

    void deleteWeekData();

    void deleteMonthData();
}
