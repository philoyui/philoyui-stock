package io.philoyui.qmier.qmiermanager.tagstock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.tagstock.entity.SarDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SarDataDao extends GenericDao<SarDataEntity,Long> {

    @Modifying
    @Query(value = "delete from SarDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
