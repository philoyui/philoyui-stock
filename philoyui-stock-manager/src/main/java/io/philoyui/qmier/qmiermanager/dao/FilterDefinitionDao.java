package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;

import java.util.List;

public interface FilterDefinitionDao extends GenericDao<FilterDefinitionEntity,Long> {
    List<FilterDefinitionEntity> findByEnable(boolean enable);
}