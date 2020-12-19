package io.philoyui.stock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.stock.dao.StockDao;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.service.StockService;
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
    public StockEntity findBySymbol(String symbol) {
        return stockDao.findBySymbol(symbol);
    }

    @Override
    public void enable(Long id) {

    }

    @Override
    public void disable(Long id) {

    }

    @Override
    public List<StockEntity> findEnable() {
        return null;
    }

    @Override
    public List<StockEntity> findAll() {
        return stockDao.findAll();
    }

    @Override
    public void fetchProductDataArray(String identifier, int pageSize) {

    }

    @Override
    public String findStockName(String symbol) {
        StockEntity stockEntity = this.findBySymbol(symbol);
        if(stockEntity==null){
            return "";
        }
        return stockEntity.getName();
    }
}
