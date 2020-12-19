package io.philoyui.tagstock.indicator.month;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TurnoverIndicatorProvider implements IndicatorProvider {


    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        return null;
    }

    @Override
    public void processGlobal() {
//        SearchFilter searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.gte("totalCapitalTurnover","0.7"));
//        searchFilter.add(Restrictions.gte("inventoryTurnover","1"));
//        searchFilter.add(Restrictions.gte("year", 2020));
//        searchFilter.add(Restrictions.gte("season", 1));
//        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);
//        List<String> symbolList = financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(symbolList,"产品强",new Date(), IntervalType.Month);
    }
}
