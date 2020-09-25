package io.philoyui.qmier.qmiermanager.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.VolumeDataEntity;

public interface VolumeDataService extends GenericService<VolumeDataEntity,Long> {
    void deleteDayData();

    void deleteWeekData();

    void deleteMonthData();
}
