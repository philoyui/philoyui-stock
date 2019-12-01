package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;

import java.util.List;

public interface DataMonthService extends GenericService<DataMonthEntity,Long> {

    void deleteBySymbol(String symbol);

    void insertAll(List<DataMonthEntity> dataMonthEntityList);

    void downloadHistory();
}
