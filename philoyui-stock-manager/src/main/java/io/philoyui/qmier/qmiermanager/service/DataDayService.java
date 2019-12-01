package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;

import java.util.List;

public interface DataDayService extends GenericService<DataDayEntity,Long> {
    void insertAll(List<DataDayEntity> dataDayEntityList);

    void deleteBySymbol(String symbol);
}
