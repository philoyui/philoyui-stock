package io.philoyui.qmier.qmiermanager.service.filter;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.StockStrategyEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 周转率，重复消费能力
 */
@Component
public class TurnoverFilter implements StockFilter{

    @Autowired
    private FinancialReportService financialReportService;

    @Override
    public Set<String> filterSymbol(StockStrategyEntity stockStrategyEntity) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gte("totalCapitalTurnover","0.7"));
        searchFilter.add(Restrictions.gte("inventoryTurnover","1"));
        searchFilter.add(Restrictions.gte("year", stockStrategyEntity.getParam1()));
        searchFilter.add(Restrictions.gte("season", stockStrategyEntity.getParam2()));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);
        return financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toSet());
    }

    @Override
    public String filterName() {
        return "turn_over";
    }

}
