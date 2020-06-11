package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.VolumeDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface VolumeDataDao extends GenericDao<VolumeDataEntity,Long> {

    @Modifying
    @Query(value = "delete from VolumeDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
