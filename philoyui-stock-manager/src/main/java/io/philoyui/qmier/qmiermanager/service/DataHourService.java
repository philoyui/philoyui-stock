package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.DataHourEntity;

import java.util.List;

public interface DataHourService extends GenericService<DataHourEntity,Long> {

    void deleteBySymbol(String symbol);

    void insertAll(List<DataHourEntity> dataHourEntityList);

    void downloadHistory();
}
