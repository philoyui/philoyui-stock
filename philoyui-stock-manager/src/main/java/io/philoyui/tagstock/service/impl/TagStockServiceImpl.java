package io.philoyui.tagstock.service.impl;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.TagService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.dao.TagStockDao;
import io.philoyui.tagstock.entity.TagEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.stock.to.TagStatistics;
import io.philoyui.stock.utils.DealDateUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

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
    public TagStockEntity tagStock(String symbol, String tagName, Date day, IntervalType intervalType,Integer lastIndex) {
        return this.tagStock(symbol,tagName,day,intervalType,lastIndex,null);
    }

    @Override
    public TagStockEntity tagStock(String symbol, String tagName, Date day, IntervalType intervalType, Integer lastIndex, Date lastDay) {

        TagStockEntity tagStockEntity = new TagStockEntity();
        tagStockEntity.setSymbol(symbol);
        tagStockEntity.setTagName(tagName);
        tagStockEntity.setCreatedTime(day);
        tagStockEntity.setDayString(DateFormatUtils.format(day,"yyyy-MM-dd"));
        tagStockEntity.setIntervalType(intervalType);
        tagStockEntity.setLastIndex(lastIndex);
        if(lastDay!=null){
            tagStockEntity.setLastDayString(DateFormatUtils.format(lastDay,"yyyy-MM-dd"));
        }
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

    @Override
    public List<TagStockEntity> findLastBySymbol(String symbol) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("symbol",symbol));
        searchFilter.add(Restrictions.eq("dayString", DealDateUtils.getLastDealDayString()));
        return list(searchFilter);
    }

    @Override
    public Map<String,String> findTagStatisticOptions() {
        List<TagStatistics> tagStatistics = tagStockDao.findTagStatistics();
        Map<String,String> keyValues = new HashMap<>();
        for (TagStatistics tagStatistic : tagStatistics) {
            keyValues.put(tagStatistic.getTagName(),tagStatistic.getTagName());
        }
        return keyValues;
    }

    @Override
    public void cleanOld(IntervalType intervalType) {
        SearchFilter pagedSearchFilter = SearchFilter.getPagedSearchFilter(0, 1000);
        pagedSearchFilter.add(Restrictions.eq("intervalType",intervalType));
        List<TagStockEntity> tagStockEntities = this.paged(pagedSearchFilter).getContent();
        tagStockDao.deleteInBatch(tagStockEntities);
    }

}
