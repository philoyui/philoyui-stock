package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagEntity;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;

import java.util.Date;
import java.util.List;

public interface TagStockService extends GenericService<TagStockEntity,Long> {

    void batchInsert(List<TagStockEntity> tagStockEntities);

    void deleteByTagName(String tagName);

    List<TagStockEntity> findBySymbol(String symbol);

    TagStockEntity tagStock(String symbol, String tagName, Date day, IntervalType intervalType, Integer lastIndex);

    TagStockEntity tagStock(String symbol, String tagName, Date day, IntervalType intervalType, Integer lastIndex,Date lastDay);

    void tagStocks(List<String> stockSet, String tagName, Date date, IntervalType day);

    List<TagStockEntity> findLastBySymbol(String symbol);
}
