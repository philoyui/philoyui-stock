package io.philoyui.stock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.stock.entity.MyStockEntity;

public interface MyStockService extends GenericService<MyStockEntity,Long> {

    /**
     * 每天获取自选股数据
     */
    void obtainEveryDay();

    void deleteAll();

    /**
     * 加入关注列表
     * @param id
     */
    void addFocus(Long id);

    String findReason(String symbol);

    MyStockEntity findBySymbol(String symbol);

    String findScore(String symbol);
}
