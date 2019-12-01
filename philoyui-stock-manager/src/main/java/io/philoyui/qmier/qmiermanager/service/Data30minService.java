package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;

import java.util.List;

public interface Data30minService extends GenericService<Data30minEntity,Long> {

    void deleteBySymbol(String symbol);

    void insertAll(List<Data30minEntity> data30minEntityList);

    void downloadHistory();

}
