package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagStockDao extends GenericDao<TagStockEntity,Long> {

    void deleteByTagName(String tagName);

    List<TagStockEntity> findBySymbol(String symbol);

    void deleteByTagNameAndDayString(String tagName, String dayString);

    TagStockEntity findFirstByOrderByCreatedTimeDesc();
}
