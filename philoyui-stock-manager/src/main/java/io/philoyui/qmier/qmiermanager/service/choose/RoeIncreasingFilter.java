package io.philoyui.qmier.qmiermanager.service.choose;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import io.philoyui.qmier.qmiermanager.service.filter.StockFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ROE 最近三年ROE上传
 */
@Component
public class RoeIncreasingFilter implements StockFilter {

    @Autowired
    private FinancialReportService financialReportService;

    /**
     *
     * 最近（year）大于18%，筛选出所有的股票，前三年股票是递增状态
     *
     * @return
     * @param param1
     * @param param2
     * @param param3
     */
    @Override
    public Set<String> filterSymbol(String param1, String param2, String param3) {

        String startYear = param1;
        String endYear = String.valueOf(Integer.parseInt(param1)+2);
        String season = param2;
        String lastMinimumRoe = param3;

        Map<String, TreeMap<String,Double>> symbolRoeMap = Maps.newHashMap();

        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("season",season));
        searchFilter.add(Restrictions.eq("year",endYear));
        searchFilter.add(Restrictions.gte("roe",lastMinimumRoe));
        List<FinancialReportEntity> financialReports = financialReportService.list(searchFilter);

        SearchFilter searchFilter1 = SearchFilter.getDefault();
        searchFilter1.add(Restrictions.in("symbol", financialReports.stream().map(FinancialReportEntity::getSymbol).toArray(String[]::new)));
        searchFilter1.add(Restrictions.gte("year",startYear));
        searchFilter1.add(Restrictions.eq("season",season));
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

        return new HashSet<String>(symbolResultList);
    }
}
