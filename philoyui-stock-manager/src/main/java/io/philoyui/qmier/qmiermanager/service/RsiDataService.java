package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.indicator.RsiDataEntity;

public interface RsiDataService extends GenericService<RsiDataEntity,Long> {
    void deleteDayData();
}
