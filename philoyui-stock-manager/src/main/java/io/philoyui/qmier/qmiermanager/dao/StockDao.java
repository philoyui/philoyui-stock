package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockDao extends GenericDao<StockEntity,Long> {

    boolean existsBySymbol(String symbol);

    StockEntity findBySymbol(String symbol);

    @Modifying
    @Query("update StockEntity m set m.enable=false")
    void markAllEnable();
}