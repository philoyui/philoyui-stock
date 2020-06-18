package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.FocusStockDao;
import io.philoyui.qmier.qmiermanager.entity.FocusStockEntity;
import io.philoyui.qmier.qmiermanager.service.FocusStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FocusStockServiceImpl extends GenericServiceImpl<FocusStockEntity,Long> implements FocusStockService {

    @Autowired
    private FocusStockDao focusStockDao;

    @Override
    protected GenericDao<FocusStockEntity, Long> getDao() {
        return focusStockDao;
    }

    @Override
    public FocusStockEntity findBySymbol(String symbol) {
        return focusStockDao.findBySymbol(symbol);
    }
}