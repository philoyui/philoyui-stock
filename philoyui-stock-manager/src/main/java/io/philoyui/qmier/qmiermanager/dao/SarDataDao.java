package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.SarDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SarDataDao extends GenericDao<SarDataEntity,Long> {

    @Modifying
    @Query(value = "delete from SarDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
