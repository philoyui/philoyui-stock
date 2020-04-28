package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;

import java.util.List;

public interface MyStockService extends GenericService<MyStockEntity,Long> {

    /**
     * 每天获取自选股数据
     */
    void obtainEveryDay();

    /**
     * 判断当前股票是否服务自选股标准
     * @param stockEntity
     * @param tagEntities
     */
    void judgeAndRecord(StockEntity stockEntity, List<TagEntity> tagEntities);
}
