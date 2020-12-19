package io.philoyui.tagstock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.tagstock.entity.MacdDataEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MacdDataDao extends GenericDao<MacdDataEntity,Long> {

    List<MacdDataEntity> findBySymbol(String symbol);

    @Modifying
    @Query(value = "delete from MacdDataEntity where interval_type = :intervalType")
    void deleteData(@Param("intervalType")String intervalType);
}
