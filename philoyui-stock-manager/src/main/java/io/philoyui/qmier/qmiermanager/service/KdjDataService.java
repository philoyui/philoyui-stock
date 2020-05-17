package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.indicator.KdjDataEntity;

public interface KdjDataService extends GenericService<KdjDataEntity,Long> {
    void deleteDayData();
}
