package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;

import java.util.List;

public interface TagStockService extends GenericService<TagStockEntity,Long> {

    void batchInsert(List<TagStockEntity> tagStockEntities);

    void deleteByTagName(String tagName);

    List<TagStockEntity> findBySymbol(String symbol);

    List<TagStockEntity> findByTagName(String tagName);
}
