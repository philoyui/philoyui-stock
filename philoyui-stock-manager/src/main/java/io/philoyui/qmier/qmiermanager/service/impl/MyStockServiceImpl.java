package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Sets;
import io.philoyui.qmier.qmiermanager.dao.MyStockDao;
import io.philoyui.qmier.qmiermanager.dao.StockDao;
import io.philoyui.qmier.qmiermanager.dao.TagDao;
import io.philoyui.qmier.qmiermanager.domain.StockAndReason;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import io.philoyui.qmier.qmiermanager.service.TagService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.apache.commons.lang3.StringUtils;
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

    @Override
    protected GenericDao<MyStockEntity, Long> getDao() {
        return myStockDao;
    }

    @Transactional
    public void obtainEveryDay(){

//        List<StockEntity> stockEntities = stockDao.findAll();
//        for (StockEntity stockEntity : stockEntities) {
//            List<TagStockEntity> tagStockEntities = tagStockService.findBySymbol(stockEntity.getSymbol());
//
//
//        }

        Set<StockAndReason> selectedStockSet = new HashSet<>();

        String dayString = tagStockService.findLastDayString();
        if(StringUtils.isEmpty(dayString)){
            dayString = DateFormatUtils.format(new Date(),"yyyy-MM-dd");
        }

        //3. 股票池，选择
        List<TagEntity> addTags = tagService.findAdd();
        for (TagEntity addTag : addTags) {
            List<TagStockEntity> addTagStocks = tagStockService.findTodayTagName(addTag.getTagName(),dayString);
            selectedStockSet.addAll(addTagStocks.stream().map(TagStockEntity::buildStockAndReason).collect(Collectors.toSet()));
        }

        //4. 根据条件过滤股票
        List<TagEntity> reduceTags = tagService.findReduce();
        for (TagEntity reduceTag : reduceTags) {
            List<TagStockEntity> reduceTagStocks = tagStockService.findTodayTagName(reduceTag.getTagName(),dayString);
            Set<StockAndReason> reduceStockSet = reduceTagStocks.stream().map(TagStockEntity::buildStockAndReason).collect(Collectors.toSet());
            selectedStockSet = Sets.difference(selectedStockSet,reduceStockSet);
        }

        selectedStockSet = selectedStockSet.stream().filter(s -> !s.getSymbol().startsWith("30")&&!s.getSymbol().startsWith("sz30")).collect(Collectors.toSet());

        String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        for (StockAndReason stockAndReason : selectedStockSet) {

            MyStockEntity myStockEntity = myStockDao.findBySymbol(stockAndReason.getSymbol());
            if(myStockEntity!=null){
                myStockEntity.setSymbol(stockAndReason.getSymbol());
                myStockEntity.setCreatedTime(new Date());
                myStockEntity.setDateString(dateStr);
                myStockEntity.setReason(myStockEntity.getReason() + "<div>" + stockAndReason.getDayString() + " " + stockAndReason.getReason()+ "</div>");
                myStockDao.save(myStockEntity);
            }else{
                myStockEntity = new MyStockEntity();
                myStockEntity.setSymbol(stockAndReason.getSymbol());
                myStockEntity.setCreatedTime(new Date());
                myStockEntity.setDateString(dateStr);
                myStockEntity.setReason( "<div>" + stockAndReason.getDayString() + " " + stockAndReason.getReason() + "</div>");
                myStockDao.save(myStockEntity);
            }

        }

    }

    @Transactional
    @Override
    public void deleteAll() {
        myStockDao.deleteAll();
    }


}
