package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;

public interface TagStockDao extends GenericDao<TagStockEntity,Long> {
    void deleteByTagName(String tagName);
}
