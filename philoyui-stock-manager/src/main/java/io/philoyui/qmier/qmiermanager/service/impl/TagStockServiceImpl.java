package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.TagStockDao;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.apache.commons.lang3.time.DateFormatUtils;
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
    public List<TagStockEntity> findByTagName(String tagName) {
        return tagStockDao.findByTagName(tagName);
    }

    @Override
    public TagStockEntity tagStock(String symbol, String tagName, Date day) {

        TagStockEntity tagStockEntity = new TagStockEntity();
        tagStockEntity.setSymbol(symbol);
        tagStockEntity.setTagName(tagName);
        tagStockEntity.setCreatedTime(day);
        tagStockEntity.setDayString(DateFormatUtils.format(day,"yyyy-MM-dd"));
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
    public void tagStocks(List<String> stockSet, String tagName, Date date) {
        List<TagStockEntity> tagStockEntities = new ArrayList<>();
        for (String symbol : stockSet) {
            TagStockEntity tagStockEntity = new TagStockEntity();
            tagStockEntity.setSymbol(symbol);
            tagStockEntity.setTagName(tagName);
            tagStockEntity.setCreatedTime(date);
            tagStockEntity.setDayString(DateFormatUtils.format(date,"yyyy-MM-dd"));
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
    public void deleteByTagNameAndDayString(String tagName, String dayString) {
        tagStockDao.deleteByTagNameAndDayString(tagName,dayString);
    }

}
