package io.philoyui.qmier.qmiermanager.service.filter;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class QuickRadioFilter implements StockFilter{

    @Autowired
    private FinancialReportService financialReportService;

    @Override
    public Set<String> filterSymbol(String param1, String param2, String param3) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.lt("quickRatio",1));
        searchFilter.add(Restrictions.eq("year",param1));
        searchFilter.add(Restrictions.eq("season",param2));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);
        return financialReports.stream().map(FinancialReportEntity::getSymbol).collect(Collectors.toSet());
    }
}
