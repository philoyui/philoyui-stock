package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Sets;
import io.philoyui.qmier.qmiermanager.dao.MyStockDao;
import io.philoyui.qmier.qmiermanager.dao.StockDao;
import io.philoyui.qmier.qmiermanager.dao.TagDao;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.StockStrategyService;
import io.philoyui.qmier.qmiermanager.service.TagService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private StockStrategyService stockStrategyService;

    @Override
    protected GenericDao<MyStockEntity, Long> getDao() {
        return myStockDao;
    }

    @Transactional
    public void obtainEveryDay(){

        //1. 删除今天的自选股
        myStockDao.deleteAll();

        Set<String> selectedStockSet = new HashSet<>();

        //3. 股票池，选择
        List<TagEntity> addTags = tagService.findAdd();
        for (TagEntity addTag : addTags) {
            List<TagStockEntity> addTagStocks = tagStockService.findByTagName(addTag.getTagName());
            selectedStockSet = addTagStocks.stream().map(TagStockEntity::getSymbol).collect(Collectors.toSet());

        }

        //4. 根据条件过滤股票
        List<TagEntity> reduceTags = tagService.findReduce();
        for (TagEntity reduceTag : reduceTags) {
            List<TagStockEntity> reduceTagStocks = tagStockService.findByTagName(reduceTag.getTagName());
            Set<String> reduceStockSet = reduceTagStocks.stream().map(TagStockEntity::getSymbol).collect(Collectors.toSet());
            selectedStockSet = Sets.difference(selectedStockSet,reduceStockSet);
        }

        selectedStockSet = selectedStockSet.stream().filter(s -> !s.startsWith("30")&&!s.startsWith("sz30")).collect(Collectors.toSet());

        String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        for (String symbol : selectedStockSet) {
            MyStockEntity myStockEntity = new MyStockEntity();
            myStockEntity.setSymbol(symbol);
            myStockEntity.setCreatedTime(new Date());
            myStockEntity.setDateString(dateStr);
            myStockDao.save(myStockEntity);
        }

    }



}
