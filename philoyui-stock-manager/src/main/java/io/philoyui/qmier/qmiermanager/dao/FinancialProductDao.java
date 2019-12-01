package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;

public interface FinancialProductDao extends GenericDao<FinancialProductEntity,Long> {

    boolean existsBySymbol(String symbol);

    FinancialProductEntity findBySymbol(String symbol);

}