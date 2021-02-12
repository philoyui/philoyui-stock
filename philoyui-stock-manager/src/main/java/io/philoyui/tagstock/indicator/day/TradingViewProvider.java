package io.philoyui.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.tradingview.entity.TradingViewEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tradingview.service.TradingViewService;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TradingViewProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private TradingViewService tradingViewService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        return null;
    }

    @Override
    public void processGlobal() {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("invertedHammer",true));
        List<TradingViewEntity> tradingViewEntities = tradingViewService.list(searchFilter);
        List<String> stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"倒锤子线",new Date(), IntervalType.Day);


        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("morningStar",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"启明星",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("triStarBullish",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"看涨三星",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("kickingBullish",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"看涨反冲形态",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("engulfingBullish",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"看涨吞没",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("haramiBullish",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"看涨孕线",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("abandonedBabyBullish",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"看涨弃婴",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("dojiDragonfly",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"蜻蜓线",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("longShadowLower",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"长下影线",new Date(), IntervalType.Day);

    }
}
