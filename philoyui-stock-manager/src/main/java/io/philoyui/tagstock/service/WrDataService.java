package io.philoyui.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.tagstock.entity.WrDataEntity;

public interface WrDataService extends GenericService<WrDataEntity,Long> {

    void deleteDayData();

    void deleteMonthData();

    void deleteWeekData();
}
