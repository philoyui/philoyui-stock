package io.philoyui.tagstock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.tagstock.entity.MaDataEntity;

public interface MaDataService extends GenericService<MaDataEntity,Long> {
    void deleteDayData();
}
