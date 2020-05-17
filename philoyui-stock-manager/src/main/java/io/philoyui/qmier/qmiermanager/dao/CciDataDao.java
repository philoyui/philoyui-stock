package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.CciDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CciDataDao extends GenericDao<CciDataEntity,Long> {

    @Modifying
    @Query(value = "delete from CciDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
