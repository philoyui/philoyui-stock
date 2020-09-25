package io.philoyui.qmier.qmiermanager.tagstock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.tagstock.entity.KdjDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface KdjDataDao extends GenericDao<KdjDataEntity,Long> {

    @Modifying
    @Query(value = "delete from KdjDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);

}
