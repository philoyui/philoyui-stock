package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.indicator.MaDataEntity;

public interface MaDataService extends GenericService<MaDataEntity,Long> {
    void deleteDayData();
}
