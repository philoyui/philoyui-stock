package io.philoyui.qmier.qmiermanager.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.MacdDataEntity;

import java.util.List;

public interface MacdDataService extends GenericService<MacdDataEntity,Long> {

    List<MacdDataEntity> findBySymbol(String symbol);

    void deleteDayData();

    void deleteWeekData();

    void deleteMonthData();
}
