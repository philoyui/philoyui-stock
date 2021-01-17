package io.philoyui.stockdetail.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.stockdetail.entity.StockDetailEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author DELL
 */
public interface StockDetailDao extends GenericDao<StockDetailEntity,Long> {

    StockDetailEntity findBySymbol(String symbol);

    @Modifying
    @Query("update StockDetailEntity u set u.dealInfo = ?2 where u.symbol in (?1)")
    void updateDealInfo(List<String> symbols, String dealInfo);

    @Modifying
    @Query("update StockDetailEntity u set u.turnOverInfo = ?2 where u.symbol in (?1)")
    void updateTurnOver(List<String> symbols, String turnOverInfo);

    @Modifying
    @Query("update StockDetailEntity u set u.epsInfo = ?2 where u.symbol in (?1)")
    void updateEpsInfo(List<String> epsList, String epsInfo);
}
