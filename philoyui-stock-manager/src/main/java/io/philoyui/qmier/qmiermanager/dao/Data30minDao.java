package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;

public interface Data30minDao extends GenericDao<Data30minEntity,Long> {
    void deleteBySymbol(String symbol);
}