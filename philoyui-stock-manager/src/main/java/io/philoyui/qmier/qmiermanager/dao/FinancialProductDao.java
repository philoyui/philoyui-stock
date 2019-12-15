package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FinancialProductDao extends GenericDao<FinancialProductEntity,Long> {

    boolean existsBySymbol(String symbol);

    FinancialProductEntity findBySymbol(String symbol);

    @Modifying
    @Query("update FinancialProductEntity m set m.enable=false")
    void markAllEnable();
}