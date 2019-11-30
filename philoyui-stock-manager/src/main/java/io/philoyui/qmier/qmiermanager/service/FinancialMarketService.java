package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.FinancialMarketEntity;

public interface FinancialMarketService extends GenericService<FinancialMarketEntity,Long> {
    FinancialMarketEntity findByIdentifier(String identifier);
}
