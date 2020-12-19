package io.philoyui.tagstock.indicator.month;

import io.philoyui.stock.entity.StockEntity;
import io.philoyui.tagstock.entity.TagStockEntity;
import io.philoyui.stock.service.TagStockService;
import io.philoyui.tagstock.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuickRadioIndicatorProvider implements IndicatorProvider {

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        return null;
    }

    @Override
    public void processGlobal() {
//        SearchFilter searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.lt("quickRatio",1));
//        searchFilter.add(Restrictions.eq("year", 2019));
//        searchFilter.add(Restrictions.eq("season", 4));
//        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);
//        List<String> symbolList = financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(symbolList,"债务风险",new Date(), IntervalType.Month);
//
//
//        searchFilter = SearchFilter.getDefault();
//        searchFilter.add(Restrictions.gte("quickRatio",1));
//        searchFilter.add(Restrictions.eq("year", 2019));
//        searchFilter.add(Restrictions.eq("season", 4));
//        financialReports = financialReportService.list(searchFilter);
//        symbolList = financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toList());
//        tagStockService.tagStocks(symbolList,"债务正常",new Date(), IntervalType.Month);
    }
}
