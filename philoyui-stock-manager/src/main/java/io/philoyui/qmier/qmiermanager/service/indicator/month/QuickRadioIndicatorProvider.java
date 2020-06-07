package io.philoyui.qmier.qmiermanager.service.indicator.month;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuickRadioIndicatorProvider implements IndicatorProvider {

    @Autowired
    private FinancialReportService financialReportService;

    @Autowired
    private TagStockService tagStockService;

    @Override
    public List<TagStockEntity> processTags(StockEntity stockEntity) {
        return null;
    }

    @Override
    public String identifier() {
        return "quick_radio";
    }

    @Override
    public void cleanOldData() {
        tagStockService.deleteByTagName("债务风险");
        tagStockService.deleteByTagName("债务正常");
    }

    @Override
    public void processGlobal() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lt("quickRatio",1));
        searchFilter.add(Restrictions.eq("year", 2019));
        searchFilter.add(Restrictions.eq("season", 4));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);
        List<String> symbolList = financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(symbolList,"债务风险",new Date(), IntervalType.Month);


        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gte("quickRatio",1));
        searchFilter.add(Restrictions.eq("year", 2019));
        searchFilter.add(Restrictions.eq("season", 4));
        financialReports = financialReportService.list(searchFilter);
        symbolList = financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(symbolList,"债务正常",new Date(), IntervalType.Month);
    }
}
