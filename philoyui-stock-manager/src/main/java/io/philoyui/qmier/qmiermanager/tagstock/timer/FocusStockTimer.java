package io.philoyui.qmier.qmiermanager.tagstock.timer;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.FocusStockEntity;
import io.philoyui.qmier.qmiermanager.service.FocusStockService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.tagstock.entity.TagStockEntity;
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
    private FocusStockService focusStockService;


    @Scheduled(cron="0 0 6 * * 2-6")
    public void execute(){

        focusStockService.deleteAll();

        Set<String> stockSet = findByTagName("MACD底背离(日)");
        Set<String> stockName2 = findByTagName("MACD底背离(15min)");
        Set<String> stockName3 = findByTagName("MACD底背离(30min)");
        Set<String> stockName4 = findByTagName("MACD底背离(60min)");
        Set<String> stockName5 = findByTagName("MACD底背离(周)");
        Set<String> stockName6 = findByTagName("KDJ底背离(周)");
        Set<String> stockName7 = findByTagName("KDJ底背离(日)");
        Set<String> stockName8 = findByTagName("RSI底背离(日)");
        Set<String> stockName9 = findByTagName("CCI底背离(日)");
        Set<String> stockName10 = findByTagName("启明星");

        Set<String> stockName11 = findByTagName("债务风险");
        Set<String> stockName12 = findByTagName("市盈率泡沫");
        Set<String> stockName13 = findByTagName("市盈率为负不盈利");

        stockSet.addAll(stockName2);
        stockSet.addAll(stockName3);
        stockSet.addAll(stockName4);
        stockSet.addAll(stockName5);
        stockSet.addAll(stockName6);
        stockSet.addAll(stockName7);
        stockSet.addAll(stockName8);
        stockSet.addAll(stockName9);
        stockSet.addAll(stockName10);

        persistStock(stockSet,4);

        stockSet.removeAll(stockName12);
        persistStock(stockSet,3);

        stockSet.removeAll(stockName13);
        persistStock(stockSet,2);

        stockSet.removeAll(stockName11);
        persistStock(stockSet,1);
    }

    private Set<String> pickLevel2Stocks() {
        Set<String> stockSet = findByTagName("MACD底背离(日)");
        Set<String> stockName2 = findByTagName("MACD底背离(15min)");
        Set<String> stockName3 = findByTagName("MACD底背离(30min)");
        Set<String> stockName4 = findByTagName("MACD底背离(60min)");
        Set<String> stockName5 = findByTagName("MACD底背离(周)");
        Set<String> stockName6 = findByTagName("KDJ底背离(周)");
        Set<String> stockName7 = findByTagName("KDJ底背离(日)");
        Set<String> stockName8 = findByTagName("RSI底背离(日)");
        Set<String> stockName9 = findByTagName("CCI底背离(日)");
        Set<String> stockName10 = findByTagName("启明星");

        Set<String> stockName11 = findByTagName("债务风险");
        Set<String> stockName12 = findByTagName("市盈率泡沫");
        Set<String> stockName13 = findByTagName("市盈率为负不盈利");

        stockSet.addAll(stockName2);
        stockSet.addAll(stockName3);
        stockSet.addAll(stockName4);
        stockSet.addAll(stockName5);
        stockSet.addAll(stockName6);
        stockSet.addAll(stockName7);
        stockSet.addAll(stockName8);
        stockSet.addAll(stockName9);
        stockSet.addAll(stockName10);

        stockSet.removeAll(stockName11);
        stockSet.removeAll(stockName12);
        stockSet.removeAll(stockName13);
        return stockSet;
    }




    private Set<String> pickLevel1Stocks() {
        Set<String> stockSet = findByTagName("MACD底背离(日)");
        Set<String> stockName2 = findByTagName("MACD底背离(15min)");
        Set<String> stockName3 = findByTagName("MACD底背离(30min)");
        Set<String> stockName4 = findByTagName("MACD底背离(60min)");
        Set<String> stockName5 = findByTagName("MACD底背离(周)");
        Set<String> stockName6 = findByTagName("KDJ底背离(周)");
        Set<String> stockName7 = findByTagName("KDJ底背离(日)");
        Set<String> stockName8 = findByTagName("RSI底背离(日)");
        Set<String> stockName9 = findByTagName("CCI底背离(日)");
        Set<String> stockName10 = findByTagName("启明星");

        Set<String> stockName11 = findByTagName("债务风险");
        Set<String> stockName12 = findByTagName("市盈率泡沫");
        Set<String> stockName13 = findByTagName("市盈率为负不盈利");

        stockSet.addAll(stockName2);
        stockSet.addAll(stockName3);
        stockSet.addAll(stockName4);
        stockSet.addAll(stockName5);
        stockSet.addAll(stockName6);
        stockSet.addAll(stockName7);
        stockSet.addAll(stockName8);
        stockSet.addAll(stockName9);
        stockSet.addAll(stockName10);

        stockSet.removeAll(stockName11);
        stockSet.removeAll(stockName12);
        stockSet.removeAll(stockName13);
        return stockSet;
    }

    private void persistStock(Set<String> stockSet, Integer level) {
        for (String symbol : stockSet) {
            FocusStockEntity focusStockEntity = new FocusStockEntity();
            focusStockEntity.setSymbol(symbol);
            focusStockEntity.setAddTime(new Date());
            List<TagStockEntity> tagStockEntities = tagStockService.findBySymbol(symbol);
            StringBuilder reasonBuilder = new StringBuilder();
            for (TagStockEntity tagStockEntity : tagStockEntities) {
                reasonBuilder.append(tagStockEntity.getTagName()).append(" ");
            }
            focusStockEntity.setReason(reasonBuilder.toString());
            focusStockEntity.setLevel(level);
            focusStockService.insert(focusStockEntity);
        }
    }

    private Set<String> findByTagName(String tagName) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("tagName",tagName));
        return tagStockService.list(searchFilter).stream().map(k->k.getSymbol()).collect(Collectors.toSet());
    }

}
