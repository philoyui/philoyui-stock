package io.philoyui.focus.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.focus.dao.FocusStockDao;
import io.philoyui.focus.entity.FocusStockEntity;
import io.philoyui.focus.service.FocusStockService;
import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.mystock.service.MyStockService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class FocusStockServiceImpl extends GenericServiceImpl<FocusStockEntity,Long> implements FocusStockService {

    @Autowired
    private FocusStockDao focusStockDao;

    @Autowired
    private MyStockService myStockService;

    @Override
    protected GenericDao<FocusStockEntity, Long> getDao() {
        return focusStockDao;
    }

    @Override
    public FocusStockEntity findBySymbol(String symbol) {
        return focusStockDao.findBySymbol(symbol);
    }

    @Override
    public void deleteAll() {
        focusStockDao.deleteAll();
    }

    @Override
    public void addToMyStock(Long id) {

        FocusStockEntity myStock = this.get(id);

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