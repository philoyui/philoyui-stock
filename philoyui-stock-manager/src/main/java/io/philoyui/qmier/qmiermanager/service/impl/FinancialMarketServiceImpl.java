package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.FinancialMarketDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialMarketEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialMarketServiceImpl extends GenericServiceImpl<FinancialMarketEntity,Long> implements FinancialMarketService {

    @Autowired
    private FinancialMarketDao financialMarketDao;

    @Override
    protected GenericDao<FinancialMarketEntity, Long> getDao() {
        return financialMarketDao;
    }

    @Override
    public FinancialMarketEntity findByIdentifier(String identifier) {
        return financialMarketDao.findByIdentifier(identifier);
    }
}