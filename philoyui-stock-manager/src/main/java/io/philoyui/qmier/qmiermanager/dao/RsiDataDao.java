package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.RsiDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RsiDataDao extends GenericDao<RsiDataEntity,Long> {
    @Modifying
    @Query(value = "delete from RsiDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
