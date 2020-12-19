package io.philoyui.tagstock.indicator.day;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.entity.TradingViewEntity;
import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.stock.service.TradingViewService;
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
//        SearchFilter searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("blackCrows3",true));
//        List<TradingViewEntity> tradingViewEntities = tradingViewService.list(searchFilter);
//        List<String> stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"三只乌鸦",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("hangingMan",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"上吊线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("invertedHammer",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"倒锤子线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("marubozuWhite",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"光头阳线",new Date(), IntervalType.Day);


//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("marubozuBlack",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"光头阴线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("doji",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"十字星",new Date(), IntervalType.Day);

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("morningStar",true));
        List<TradingViewEntity> tradingViewEntities = tradingViewService.list(searchFilter);
        List<String> stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"启明星",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("gravestone",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"墓碑线",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("shootingStar",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"流星线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("whiteSoldiers3",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"白三兵",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("spinningTopWhite",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"白色旋转陀螺",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("triStarBullish",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"看涨三星",new Date(), IntervalType.Day);

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

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("haramiBullish",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"看涨孕线",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("abandonedBabyBullish",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"看涨弃婴",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("triStarBearish",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"看跌三星",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("kickingBearish",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"看跌反冲形态",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("engulfingBearish",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"看跌吞没",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("haramiBearish",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"看跌孕线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("abandonedBabyBearish",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"看跌弃婴",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("dojiDragonfly",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"蜻蜓线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("hammer",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"锤子线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("longShadowUpper",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"长上影线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("spinningTopBlack",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"黑色旋转陀螺",new Date(), IntervalType.Day);

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("longShadowLower",true));
        tradingViewEntities = tradingViewService.list(searchFilter);
        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(stockList,"长下影线",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.eq("eveningStar",true));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"黄昏星",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.gte("recommendScore",0.5));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"TradingView强烈买入",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.lt("recommendScore",0.5));
//        searchFilter.add(Restrictions.gt("recommendScore",0));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"TradingView买入",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.lt("recommendScore",0));
//        searchFilter.add(Restrictions.gte("recommendScore",-0.5));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"TradingView卖出",new Date(), IntervalType.Day);

//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.lt("recommendScore",-0.5));
//        tradingViewEntities = tradingViewService.list(searchFilter);
//        stockList = tradingViewEntities.stream().map(TradingViewEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(stockList,"TradingView强烈卖出",new Date(), IntervalType.Day);

    }
}
