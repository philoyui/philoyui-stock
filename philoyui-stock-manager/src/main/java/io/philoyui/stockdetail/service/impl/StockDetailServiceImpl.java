package io.philoyui.stockdetail.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.stockdetail.dao.StockDetailDao;
import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockDetailServiceImpl extends GenericServiceImpl<StockDetailEntity,Long> implements StockDetailService {

    @Autowired
    private StockDetailDao stockDetailDao;

    @Override
    protected GenericDao<StockDetailEntity, Long> getDao() {
        return stockDetailDao;
    }

    @Override
    public StockDetailEntity findBySymbol(String symbol) {
        return stockDetailDao.findBySymbol(symbol);
    }
}
