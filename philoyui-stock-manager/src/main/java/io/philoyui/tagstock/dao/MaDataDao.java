package io.philoyui.tagstock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.tagstock.entity.MaDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MaDataDao extends GenericDao<MaDataEntity,Long> {

    @Modifying
    @Query(value = "delete from MaDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
