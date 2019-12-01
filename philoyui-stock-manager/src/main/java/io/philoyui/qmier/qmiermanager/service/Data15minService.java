package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.Data15minEntity;

import java.util.List;

public interface Data15minService extends GenericService<Data15minEntity,Long> {

    void insertAll(List<Data15minEntity> data15minEntityList);

    void deleteBySymbol(String symbol);

    void downloadHistory();

}
