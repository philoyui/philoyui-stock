package io.philoyui.qmier.qmiermanager.service.indicator.month;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class TurnoverIndicatorProvider implements IndicatorProvider {

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
        return "turn_over";
    }

    @Override
    public void cleanOldData() {
        String dayString = DateFormatUtils.format(new Date(),"yyyy-MM-dd");
        tagStockService.deleteByTagNameAndDayString("产品强",dayString);
    }

    @Override
    public void processGlobal() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gte("totalCapitalTurnover","0.7"));
        searchFilter.add(Restrictions.gte("inventoryTurnover","1"));
        searchFilter.add(Restrictions.gte("year", 2019));
        searchFilter.add(Restrictions.gte("season", 3));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);
        List<String> symbolList = financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toList());
        tagStockService.tagStocks(symbolList,"产品强",new Date());
    }
}
