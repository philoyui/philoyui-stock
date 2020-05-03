package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.indicator.MacdDataEntity;

import java.util.List;

public interface MacdDataService extends GenericService<MacdDataEntity,Long> {

    List<MacdDataEntity> findBySymbol(String symbol);

    void deleteDayData();

    void deleteWeekData();
}
