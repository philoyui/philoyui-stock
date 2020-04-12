package io.philoyui.qmier.qmiermanager.service.tag;

import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public abstract class TagMarker implements Serializable {

    @Autowired
    private TagStockService tagStockService;

    public abstract void processGlobal();

    public abstract void processEachStock(ProcessorContext processorContext, StockEntity stockEntity);

    protected void tagStocks(List<String> symbolList, String tagName){
        List<TagStockEntity> tagStockEntities = Lists.newArrayList();
        for (String symbol : symbolList) {
            TagStockEntity tagStockEntity = new TagStockEntity();
            tagStockEntity.setSymbol(symbol);
            tagStockEntity.setTagName(tagName);
            tagStockEntity.setCreatedTime(new Date());
            tagStockEntities.add(tagStockEntity);
        }
        tagStockService.batchInsert(tagStockEntities);
    }

    protected void tagStocks(String symbol, String tagName){
        TagStockEntity tagStockEntity = new TagStockEntity();
        tagStockEntity.setSymbol(symbol);
        tagStockEntity.setTagName(tagName);
        tagStockEntity.setCreatedTime(new Date());
        tagStockService.insert(tagStockEntity);
    }

    public boolean supportDate(){
        return false;
    }

    public boolean supportWeek(){
        return false;
    }

    public boolean supportMonth(){
        return false;
    }

    public abstract boolean isGlobal();
}
