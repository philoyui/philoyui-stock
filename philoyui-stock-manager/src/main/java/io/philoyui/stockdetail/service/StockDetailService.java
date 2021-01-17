package io.philoyui.stockdetail.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.stockdetail.entity.StockDetailEntity;

import java.util.List;

public interface StockDetailService extends GenericService<StockDetailEntity,Long> {

    StockDetailEntity findBySymbol(String symbol);

    void updateDealInfo(List<String> bigBigDeal, String dealInfo);

    void updateTurnOver(List<String> fetchHighTurnOver, String turnOverInfo);

    void updateEpsInfo(List<String> epsList, String epsInfo);
}
