package io.philoyui.qmier.qmiermanager.tagstock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.to.TagStatistics;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagStockDao extends GenericDao<TagStockEntity,Long> {

    void deleteByTagName(String tagName);

    List<TagStockEntity> findBySymbol(String symbol);

    @Query("SELECT tagName as tagName,COUNT(*) as tagCount from TagStockEntity t GROUP BY tagName ORDER BY tagCount DESC")
    List<TagStatistics> findTagStatistics();

}
