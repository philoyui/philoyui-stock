package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.StockDao;
import io.philoyui.qmier.qmiermanager.entity.stock.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockServiceImpl extends GenericServiceImpl<StockEntity,Long> implements StockService {

    @Autowired
    private StockDao stockDao;

    @Override
    protected GenericDao<StockEntity, Long> getDao() {
        return stockDao;
    }

    @Override
    public void insertAll(List<StockEntity> stockEntity) {
        stockDao.saveAll(stockEntity);
    }
}
