package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.DataWeekEntity;

import java.util.List;

public interface DataWeekService extends GenericService<DataWeekEntity,Long> {
    void insertAll(List<DataWeekEntity> dataWeekEntityList);

    void deleteBySymbol(String symbol);
}
