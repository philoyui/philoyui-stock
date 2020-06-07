package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.dao.TagStockDao;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.service.TagService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.utils.DealDateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class TagStockServiceImpl extends GenericServiceImpl<TagStockEntity,Long> implements TagStockService {

    @Autowired
    private TagStockDao tagStockDao;

    @Autowired
    private TagService tagService;

    @Override
    protected GenericDao<TagStockEntity, Long> getDao() {
        return tagStockDao;
    }

    @Override
    public void batchInsert(List<TagStockEntity> tagStockEntities) {
        tagStockDao.saveAll(tagStockEntities);
    }

    @Transactional
    @Override
    public void deleteByTagName(String tagName) {
        tagStockDao.deleteByTagName(tagName);
    }

    @Override
    public List<TagStockEntity> findBySymbol(String symbol) {
        return tagStockDao.findBySymbol(symbol);
    }

    @Override
    public List<TagStockEntity> findCurrentTagName(TagEntity tagEntity) {

        switch (tagEntity.getIntervalType()){
            case Day:
                String dayString = DealDateUtils.getLastDealDayString();
                SearchFilter searchFilter = SearchFilter.getDefault();
                searchFilter.add(Restrictions.eq("tagName",tagEntity.getTagName()));
                searchFilter.add(Restrictions.eq("dayString", dayString));
                return list(searchFilter);
            case Week:
                searchFilter = SearchFilter.getDefault();
                searchFilter.add(Restrictions.eq("tagName",tagEntity.getTagName()));
                searchFilter.add(Restrictions.gt("createdTime", DateUtils.addDays(new Date(),-7)));
                searchFilter.add(Restrictions.lt("createdTime", new Date()));
                return list(searchFilter);
            case Month:
                searchFilter = SearchFilter.getDefault();
                searchFilter.add(Restrictions.eq("tagName",tagEntity.getTagName()));
                searchFilter.add(Restrictions.gt("createdTime", DateUtils.addDays(new Date(),-31)));
                searchFilter.add(Restrictions.lt("createdTime", new Date()));
                return list(searchFilter);
        }

        return Lists.newArrayList();

    }

    @Override
    public TagStockEntity tagStock(String symbol, String tagName, Date day, IntervalType intervalType,Integer lastIndex) {

        TagStockEntity tagStockEntity = new TagStockEntity();
        tagStockEntity.setSymbol(symbol);
        tagStockEntity.setTagName(tagName);
        tagStockEntity.setCreatedTime(day);
        tagStockEntity.setDayString(DateFormatUtils.format(day,"yyyy-MM-dd"));
        tagStockEntity.setIntervalType(intervalType);
        tagStockEntity.setLastIndex(lastIndex);
        this.insert(tagStockEntity);

        TagEntity tagEntity = tagService.findByTagName(tagName);
        if(tagEntity!=null){
            tagEntity.setLastExecuteTime(new Date());
            tagService.update(tagEntity);
        }else{
            tagEntity = new TagEntity();
            tagEntity.setTagName(tagName);
            tagEntity.setLastExecuteTime(new Date());
            tagService.update(tagEntity);
        }

        return tagStockEntity;
    }

    @Override
    public void tagStocks(List<String> stockSet, String tagName, Date date, IntervalType intervalType) {
        List<TagStockEntity> tagStockEntities = new ArrayList<>();
        for (String symbol : stockSet) {
            TagStockEntity tagStockEntity = new TagStockEntity();
            tagStockEntity.setSymbol(symbol);
            tagStockEntity.setTagName(tagName);
            tagStockEntity.setCreatedTime(date);
            tagStockEntity.setDayString(DateFormatUtils.format(date,"yyyy-MM-dd"));
            tagStockEntity.setIntervalType(intervalType);
            tagStockEntity.setLastIndex(-1);
            tagStockEntities.add(tagStockEntity);
        }
        this.batchInsert(tagStockEntities);

        TagEntity tagEntity = tagService.findByTagName(tagName);
        if(tagEntity!=null){
            tagEntity.setLastExecuteTime(new Date());
            tagService.update(tagEntity);
        }else{
            tagEntity = new TagEntity();
            tagEntity.setTagName(tagName);
            tagEntity.setLastExecuteTime(new Date());
            tagService.update(tagEntity);
        }

    }

    @Transactional
    @Override
    public void deleteByTagNameAndDayString(String tagName, String dayString) {
        tagStockDao.deleteByTagNameAndDayString(tagName,dayString);
    }

    @Override
    public String findLastDayString() {
        TagStockEntity tagStockEntity = tagStockDao.findFirstByOrderByCreatedTimeDesc();
        return tagStockEntity.getDayString();
    }

    @Override
    public List<TagStockEntity> findLastBySymbol(String symbol) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("symbol",symbol));
        searchFilter.add(Restrictions.eq("dayString", DealDateUtils.getLastDealDayString()));
        return list(searchFilter);
    }

}
