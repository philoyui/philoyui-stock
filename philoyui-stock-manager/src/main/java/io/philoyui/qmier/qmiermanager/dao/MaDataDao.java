package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.MaDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MaDataDao extends GenericDao<MaDataEntity,Long> {

    @Modifying
    @Query(value = "delete from MaDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
