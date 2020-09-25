package io.philoyui.qmier.qmiermanager.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.MaDataEntity;

public interface MaDataService extends GenericService<MaDataEntity,Long> {
    void deleteDayData();
}
