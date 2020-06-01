package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;

import java.util.BitSet;
import java.util.Date;
import java.util.List;

public interface TagStockService extends GenericService<TagStockEntity,Long> {

    void batchInsert(List<TagStockEntity> tagStockEntities);

    void deleteByTagName(String tagName);

    List<TagStockEntity> findBySymbol(String symbol);

    List<TagStockEntity> findTodayTagName(String tagName,String dayString);

    TagStockEntity tagStock(String symbol, String tagName, Date day);

    void tagStocks(List<String> stockSet, String tagName, Date date);

    void deleteByTagNameAndDayString(String tagName, String dayString);

    String findLastDayString();

    List<TagStockEntity> findLastBySymbol(String symbol);
}
