package io.philoyui.qmier.qmiermanager.service.tag.processor;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import io.philoyui.qmier.qmiermanager.service.tag.GlobalTagMarker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TurnoverTagMarker extends GlobalTagMarker {

    @Autowired
    private FinancialReportService financialReportService;

    @Override
    public void processGlobal() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gte("totalCapitalTurnover","0.7"));
        searchFilter.add(Restrictions.gte("inventoryTurnover","1"));
        searchFilter.add(Restrictions.gte("year", 2019));
        searchFilter.add(Restrictions.gte("season", 3));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);
        List<String> symbolList = financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toList());
        this.tagStocks(symbolList,"产品强");
    }

    @Override
    public boolean supportDate() {
        return false;
    }

    @Override
    public boolean supportWeek() {
        return false;
    }

    @Override
    public boolean supportMonth() {
        return true;
    }

}
