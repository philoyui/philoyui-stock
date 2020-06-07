package io.philoyui.qmier.qmiermanager.service.indicator.month;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.common.collect.Lists;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import io.philoyui.qmier.qmiermanager.service.TagStockService;
import io.philoyui.qmier.qmiermanager.service.indicator.IndicatorProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoeIndicatorProvider implements IndicatorProvider {

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
        return "roe";
    }

    @Override
    public void cleanOldData() {
        tagStockService.deleteByTagName("业绩上升");
        tagStockService.deleteByTagName("存股");
    }

    @Override
    public void processGlobal() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("season",1));
        searchFilter.add(Restrictions.eq("year",2020));
        searchFilter.add(Restrictions.gte("roe",0));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);

        SearchFilter searchFilter1 = SearchFilter.getDefault();
        searchFilter1.add(Restrictions.in("symbol", financialReports.stream().map(FinancialReportEntity::getSymbol).toArray(String[]::new)));
        searchFilter1.add(Restrictions.gte("year",2017));
        searchFilter1.add(Restrictions.eq("season",4));
        List<FinancialReportEntity> financialReport1s = financialReportService.list(searchFilter1);

        Map<String, List<FinancialReportEntity>> symbolReportMap = financialReport1s.stream().sorted(Comparator.comparing(FinancialReportEntity::getYear)).collect(Collectors.groupingBy(FinancialReportEntity::getSymbol, Collectors.toList()));

        List<String> symbolResultList = Lists.newArrayList();

        for (Map.Entry<String, List<FinancialReportEntity>> stringSetEntry : symbolReportMap.entrySet()) {
            if(stringSetEntry.getValue().size()==3&&stringSetEntry.getValue().get(0).getRoe()!=null&&stringSetEntry.getValue().get(1).getRoe()!=null&&stringSetEntry.getValue().get(2).getRoe()!=null){
                if(stringSetEntry.getValue().get(0).getRoe()<stringSetEntry.getValue().get(1).getRoe()&&stringSetEntry.getValue().get(1).getRoe()<stringSetEntry.getValue().get(2).getRoe()){
                    symbolResultList.add(stringSetEntry.getKey());
                }
            }
        }

        tagStockService.tagStocks(symbolResultList,"业绩上升",new Date(), IntervalType.Month);

        //===========================================================

        searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.gte("year",2017));
        searchFilter.add(Restrictions.gte("season", 4));
        searchFilter.add(Restrictions.gte("roe",18));
        financialReports = financialReportService.list(searchFilter);

        Map<String, Long> symbolCountMap = financialReports.stream().collect(Collectors.groupingBy(FinancialReportEntity::getSymbol, Collectors.counting()));

        List<String> resultSet = new ArrayList<>();
        for (Map.Entry<String, Long> stringLongEntry : symbolCountMap.entrySet()) {
            if(stringLongEntry.getValue()==5){
                resultSet.add(stringLongEntry.getKey());
            }
        }

        tagStockService.tagStocks(resultSet,"存股",new Date(), IntervalType.Month);
    }
}
