package io.philoyui.mystock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.mystock.entity.MyStockEntity;

public interface MyStockService extends GenericService<MyStockEntity,Long> {

    void deleteAll();

    String findReason(String symbol);

    MyStockEntity findBySymbol(String symbol);

    String findScore(String symbol);
}
