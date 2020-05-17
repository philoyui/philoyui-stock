package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.WrDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface WrDataDao extends GenericDao<WrDataEntity,Long> {

    @Modifying
    @Query(value = "delete from WrDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
