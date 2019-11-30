package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialMarketEntity;

public interface FinancialMarketDao extends GenericDao<FinancialMarketEntity,Long> {
    FinancialMarketEntity findByIdentifier(String identifier);
}