package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.Data15minEntity;

public interface Data15minDao extends GenericDao<Data15minEntity,Long> {
    void deleteBySymbol(String symbol);
}