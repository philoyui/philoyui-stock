package io.philoyui.tagstock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.tagstock.entity.CciDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CciDataDao extends GenericDao<CciDataEntity,Long> {

    @Modifying
    @Query(value = "delete from CciDataEntity where interval_type = :intervalType")
    void deleteData(String intervalType);
}
