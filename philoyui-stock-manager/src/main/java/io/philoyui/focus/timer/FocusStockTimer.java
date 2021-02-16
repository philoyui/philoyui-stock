package io.philoyui.focus.timer;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.common.collect.Sets;
import io.philoyui.focus.entity.FocusStockEntity;
import io.philoyui.focus.service.FocusStockService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tradingview.entity.TradingViewEntity;
import io.philoyui.tradingview.service.TradingViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FocusStockTimer {

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private TradingViewService tradingViewService;

    @Autowired
    private FocusStockService focusStockService;

    @Autowired
    private StockDetailService stockDetailService;


    @Scheduled(cron="0 0 6 * * 2-6")
    public void execute(){

        focusStockService.deleteAll();

        Set<String> bollUpStockSet = findBollUp();

        Set<String> stockSet = findByTagName("MACD底背离(日)");
//        Set<String> stockName2 = findByTagName("MACD底背离(15min)");
//        Set<String> stockName3 = findByTagName("MACD底背离(30min)");
        Set<String> stockName4 = findByTagName("MACD底背离(60min)");
        Set<String> stockName5 = findByTagName("MACD底背离(周)");
        Set<String> stockName8 = findByTagName("RSI底背离(日)");
        Set<String> stockName9 = findByTagName("CCI底背离(日)");
        Set<String> stockName10 = findByTagName("启明星");

        Set<String> stockName14 = findByTagName("MACD顶背离(日)");
        Set<String> stockName15 = findByTagName("MACD顶背离(15min)");
        Set<String> stockName16 = findByTagName("MACD顶背离(30min)");
        Set<String> stockName17 = findByTagName("MACD顶背离(60min)");
        Set<String> stockName18 = findByTagName("MACD顶背离(周)");
        Set<String> stockName19 = findByTagName("RSI顶背离(日)");
        Set<String> stockName20 = findByTagName("CCI顶背离(日)");




        Set<String> stockName11 = findByTagName("债务风险");
        Set<String> stockName12 = findByTagName("市盈率泡沫");
        Set<String> stockName13 = findByTagName("市盈率为负不盈利");

        Set<String> stockName21 = findByTagName("大宗交易");
        Set<String> stockName22 = findByTagName("大容量大宗交易");
        Set<String> stockName23 = findByTagName("溢价大宗交易");

//        stockSet.addAll(stockName2);
//        stockSet.addAll(stockName3);
        stockSet.addAll(stockName4);
        stockSet.addAll(stockName5);
        stockSet.addAll(stockName8);
        stockSet.addAll(stockName9);
        stockSet.addAll(stockName10);
        stockSet.addAll(stockName21);
        stockSet.addAll(stockName22);
        stockSet.addAll(stockName23);


        //排除顶背离，
        stockSet.removeAll(stockName14);
        stockSet.removeAll(stockName15);
        stockSet.removeAll(stockName16);
        stockSet.removeAll(stockName17);
        stockSet.removeAll(stockName18);
        stockSet.removeAll(stockName19);
        stockSet.removeAll(stockName20);

        stockSet = Sets.intersection(bollUpStockSet,stockSet);

        persistStock(stockSet,"底背离-顶背离-BOLL上轨道",5);

        handlePattern(stockSet);

    }

    private void handlePattern(Set<String> stockSet) {
        SearchFilter searchFilter10 = SearchFilter.getDefault();
        searchFilter10.add(Restrictions.or(Restrictions.eq("morningStar",true),Restrictions.eq("triStarBullish",true),Restrictions.eq("kickingBullish",true)
                ,Restrictions.eq("engulfingBullish",true),Restrictions.eq("haramiBullish",true),Restrictions.eq("abandonedBabyBullish",true)
                ,Restrictions.eq("haramiBullish",true),Restrictions.eq("longShadowLower",true),Restrictions.eq("dojiDragonfly",true)
        ));

        Set<String> stockName21 = tradingViewService.list(searchFilter10).stream().map(TradingViewEntity::getSymbol).collect(Collectors.toSet());
        persistStock(Sets.difference(stockName21, stockSet),"看涨形态",6);
    }

    private Set<String> findBollUp() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("isInUpperBoll",1));
        return tradingViewService.list(searchFilter).stream().map(TradingViewEntity::getSymbol).collect(Collectors.toSet());
    }

    private void persistStock(Set<String> stockSet, String reason,int level) {
        for (String symbol : stockSet) {
            FocusStockEntity focusStockEntity = new FocusStockEntity();
            focusStockEntity.setSymbol(symbol);
            focusStockEntity.setAddTime(new Date());

            List<TagStockEntity> tagStockEntities = tagStockService.findBySymbol(symbol);
            StringBuilder reasonBuilder = new StringBuilder();
            StringBuilder reportBuilder = new StringBuilder();
            for (TagStockEntity tagStockEntity : tagStockEntities) {
                reasonBuilder.append("<p>").append(tagStockEntity.getTagName()).append("</p>");
            }

            StockDetailEntity stockDetailEntity = stockDetailService.findBySymbol(symbol);
            if(stockDetailEntity!=null){
                List<String> technicalItems = stockDetailEntity.buildTechnicalItems();
                List<String> reportItems = stockDetailEntity.buildReportItems();
                for (String technicalItem : technicalItems) {
                    reasonBuilder.append("<p>").append(technicalItem).append("</p>");
                }
                for (String describeItem : reportItems) {
                    reportBuilder.append("<p>").append(describeItem).append("</p>");
                }
            }

            TradingViewEntity tradingViewEntity = tradingViewService.findBySymbol(symbol);
            if(tradingViewEntity!=null){
                List<String> tradingViewReports = tradingViewEntity.buildTradingViewItems();
                for (String describeItem : tradingViewReports) {
                    reasonBuilder.append("<p>").append(describeItem).append("</p>");
                }
            }

            focusStockEntity.setTags(reasonBuilder.toString());
            focusStockEntity.setReason(reason);
            focusStockEntity.setLevel(level);
            focusStockEntity.setFinancialReport(reportBuilder.toString());
            focusStockService.insert(focusStockEntity);
        }
    }

    private Set<String> findByTagName(String tagName) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("tagName",tagName));
        return tagStockService.list(searchFilter).stream().map(TagStockEntity::getSymbol).collect(Collectors.toSet());
    }

}
