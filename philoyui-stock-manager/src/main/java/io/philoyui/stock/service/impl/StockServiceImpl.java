package io.philoyui.stock.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.mystock.service.MyStockService;
import io.philoyui.stock.dao.StockDao;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.service.StockService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class StockServiceImpl extends GenericServiceImpl<StockEntity,Long> implements StockService {

    @Autowired
    private StockDao stockDao;

    @Autowired
    private MyStockService myStockService;

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

    @Override
    public void addToMyStock(Long id) {
        StockEntity myStock = this.get(id);

        MyStockEntity myStockEntity = myStockService.findBySymbol(myStock.getSymbol());

        if(myStockEntity==null){
            myStockEntity = new MyStockEntity();
            myStockEntity.setDateString(DateFormatUtils.format(new Date(),"yyyy-MM-dd"));
            myStockEntity.setStockName(myStockEntity.getStockName());
            myStockEntity.setSymbol(myStock.getSymbol());
            myStockEntity.setCreatedTime(new Date());
            myStockService.insert(myStockEntity);
        }
    }
}
