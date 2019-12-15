package io.philoyui.qmier.qmiermanager.service.filter;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import org.jsoup.select.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoeStableFilter implements StockFilter{

    @Autowired
    private FinancialReportService financialReportService;

    @Override
    public Set<String> filterSymbol() {

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gte("roe","18"));
        searchFilter.add(Restrictions.gte("year","2014"));
        searchFilter.add(Restrictions.gte("season","年报"));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);

        Map<String, Long> symbolCountMap = financialReports.stream().collect(Collectors.groupingBy(FinancialReportEntity::getSymbol, Collectors.counting()));

        Set<String> resultSet = new HashSet<>();
        for (Map.Entry<String, Long> stringLongEntry : symbolCountMap.entrySet()) {
            if(stringLongEntry.getValue()==5){
                resultSet.add(stringLongEntry.getKey());
            }
        }

        return resultSet;
    }
}
