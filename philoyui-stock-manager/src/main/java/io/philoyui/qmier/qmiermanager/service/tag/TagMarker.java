package io.philoyui.qmier.qmiermanager.service.tag;

import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class TagMarker implements Serializable {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private TagService tagService;

    public abstract void processGlobal();

    public abstract void processEachStock(ProcessorContext processorContext, StockEntity stockEntity, String prefix);

    protected void tagStocks(List<String> symbolList, String tagName){

        tagStockService.deleteByTagName(tagName);
        List<TagStockEntity> tagStockEntities = Lists.newArrayList();
        for (String symbol : symbolList) {
            TagStockEntity tagStockEntity = new TagStockEntity();
            tagStockEntity.setSymbol(symbol);
            tagStockEntity.setTagName(tagName);
            tagStockEntity.setCreatedTime(new Date());
            tagStockEntities.add(tagStockEntity);
        }
        tagStockService.batchInsert(tagStockEntities);

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

    protected void tagStocks(String symbol, String tagName){
        TagStockEntity tagStockEntity = new TagStockEntity();
        tagStockEntity.setSymbol(symbol);
        tagStockEntity.setTagName(tagName);
        tagStockEntity.setCreatedTime(new Date());
        tagStockService.insert(tagStockEntity);
    }

    public abstract boolean isGlobal();

    public abstract void cleanTags();

    protected void deleteStocks(String tagName) {
        tagStockService.deleteByTagName(tagName);

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

    public abstract boolean supportDate();

    public abstract boolean supportWeek();

    public abstract boolean supportMonth();
}
