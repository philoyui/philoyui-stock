package io.philoyui.mystock.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Lists;
import io.philoyui.focus.service.FocusStockService;
import io.philoyui.mystock.dao.MyStockDao;
import io.philoyui.mystock.entity.MyStockEntity;
import io.philoyui.mystock.service.MyStockService;
import io.philoyui.stock.dao.StockDao;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.service.TagService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.dao.TagDao;
import io.philoyui.tagstock.entity.TagEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MyStockServiceImpl extends GenericServiceImpl<MyStockEntity,Long> implements MyStockService {

    @Autowired
    private MyStockDao myStockDao;

    @Autowired
    private StockDao stockDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private TagService tagService;

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private MyStockService myStockService;

    @Autowired
    private FocusStockService focusStockService;

    @Override
    protected GenericDao<MyStockEntity, Long> getDao() {
        return myStockDao;
    }


    @Transactional
    @Override
    public void deleteAll() {
        myStockDao.deleteAll();
    }

    @Override
    public String findReason(String symbol) {
        MyStockEntity stockEntity = myStockDao.findBySymbol(symbol);
        if(stockEntity!=null){
            return stockEntity.getReason();
        }
        return "";
    }

    @Override
    public MyStockEntity findBySymbol(String symbol) {
        return myStockDao.findBySymbol(symbol);
    }

    @Override
    public String findScore(String symbol) {
        MyStockEntity bySymbol = this.findBySymbol(symbol);
        if(bySymbol!=null){
            return String.valueOf(bySymbol.getScore());
        }
        return "";
    }

}
