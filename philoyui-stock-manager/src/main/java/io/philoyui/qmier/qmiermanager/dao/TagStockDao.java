package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;

import java.util.List;

public interface TagStockDao extends GenericDao<TagStockEntity,Long> {

    void deleteByTagName(String tagName);

    List<TagStockEntity> findBySymbol(String symbol);

    List<TagStockEntity> findByTagName(String tagName);

    void deleteByTagNameAndDayString(String tagName, String dayString);
}
