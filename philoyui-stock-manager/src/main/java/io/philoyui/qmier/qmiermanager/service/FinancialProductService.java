package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;

import java.util.List;

public interface FinancialProductService extends GenericService<FinancialProductEntity,Long> {

    boolean existsBySymbol(String symbol);

    FinancialProductEntity findBySymbol(String symbol);

    void enable(Long id);

    void disable(Long id);

    List<FinancialProductEntity> findEnable();

    void downloadHistoryData();

    void markAllDisable();

    void updateAll(List<FinancialProductEntity> financialProductEntityList);
}
