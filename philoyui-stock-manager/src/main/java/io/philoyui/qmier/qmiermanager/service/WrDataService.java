package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.indicator.WrDataEntity;

public interface WrDataService extends GenericService<WrDataEntity,Long> {

    void deleteDayData();

    void deleteMonthData();

    void deleteWeekData();
}
