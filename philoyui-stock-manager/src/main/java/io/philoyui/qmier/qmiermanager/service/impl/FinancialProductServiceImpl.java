package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.FinancialProductDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialProductServiceImpl extends GenericServiceImpl<FinancialProductEntity,Long> implements FinancialProductService {

    @Autowired
    private FinancialProductDao financialProductDao;

    @Override
    protected GenericDao<FinancialProductEntity, Long> getDao() {
        return financialProductDao;
    }

    @Override
    public boolean existsBySymbol(String symbol) {
        return financialProductDao.existsBySymbol(symbol);
    }

    @Override
    public FinancialProductEntity findBySymbol(String symbol) {
        return financialProductDao.findBySymbol(symbol);
    }
}