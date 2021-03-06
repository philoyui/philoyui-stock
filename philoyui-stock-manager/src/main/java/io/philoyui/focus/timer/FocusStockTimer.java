package io.philoyui.focus.timer;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.common.base.Strings;
import io.philoyui.focus.entity.FocusStockEntity;
import io.philoyui.focus.service.FocusStockService;
import io.philoyui.stock.service.StockService;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.stockdetail.entity.StockDetailEntity;
import io.philoyui.stockdetail.service.StockDetailService;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tradingview.entity.TradingViewEntity;
import io.philoyui.tradingview.service.TradingViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FocusStockTimer {

    private static final Logger LOG = LoggerFactory.getLogger(FocusStockTimer.class);

    @Autowired
    private TagStockService tagStockService;

    @Autowired
    private TradingViewService tradingViewService;

    @Autowired
    private FocusStockService focusStockService;

    @Autowired
    private StockDetailService stockDetailService;

    @Autowired
    private StockService stockService;


    @Scheduled(cron="0 0 6 * * 2-6")
    public void execute(){

        LOG.info("----定时器（提取待关注股票）开始执行：");

        focusStockService.deleteAll();

        Set<String> stockSet = findByTagName("MACD底背离(日)");
//        Set<String> stockName2 = findByTagName("MACD底背离(15min)");
        Set<String> stockName3 = findByTagName("MACD底背离(30min)");
        Set<String> stockName4 = findByTagName("MACD底背离(60min)");
        Set<String> stockName5 = findByTagName("MACD底背离(周)");
//        Set<String> stockName8 = findByTagName("RSI底背离(日)");
//        Set<String> stockName9 = findByTagName("CCI底背离(日)");

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

        stockSet.addAll(stockName3);
        stockSet.addAll(stockName4);
        stockSet.addAll(stockName5);
//        stockSet.addAll(stockName8);
//        stockSet.addAll(stockName9);

        //排除顶背离，
        stockSet.removeAll(stockName14);
        stockSet.removeAll(stockName15);
        stockSet.removeAll(stockName16);
        stockSet.removeAll(stockName17);
        stockSet.removeAll(stockName18);
        stockSet.removeAll(stockName19);
        stockSet.removeAll(stockName20);

        persistStock(stockSet,"底背离-顶背离-BOLL上轨道",5);


    }

    private void persistStock(Set<String> stockSet, String reason,int level) {
        for (String symbol : stockSet) {

            String stockName = stockService.findStockName(symbol);
            if(Strings.isNullOrEmpty(stockName)){
               continue;
            }
            if(stockName.startsWith("ST")||stockName.startsWith("*ST")){
                continue;
            }

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
        Set<String> collect = tagStockService.list(searchFilter).stream().map(TagStockEntity::getSymbol).collect(Collectors.toSet());
        LOG.info("发现" + tagName + "股票共" + collect.size() + "条");
        return collect;
    }

}
