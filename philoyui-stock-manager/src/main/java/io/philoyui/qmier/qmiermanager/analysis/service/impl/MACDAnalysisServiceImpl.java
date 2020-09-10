package io.philoyui.qmier.qmiermanager.analysis.service.impl;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.analysis.domain.TimeData;
import io.philoyui.qmier.qmiermanager.analysis.service.MACDAnalysisService;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.MacdDataEntity;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.MacdType;
import io.philoyui.qmier.qmiermanager.service.MacdDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MACDAnalysisServiceImpl implements MACDAnalysisService {

    @Autowired
    private MacdDataService macdDataService;

    @Override
    public List<TimeData> findLastAxis0Cross(String symbol) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",symbol));
        searchFilter.add(Order.desc("day"));
        List<MacdDataEntity> macdDataEntities = macdDataService.list(searchFilter);

        List<MacdDataEntity> goldenDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.GOLDEN_CROSS).collect(Collectors.toList());

        Optional<MacdDataEntity> max = macdDataEntities.stream().max(Comparator.comparing(MacdDataEntity::getMacdValue));
        Optional<MacdDataEntity> min = macdDataEntities.stream().min(Comparator.comparing(MacdDataEntity::getMacdValue));

        if(max.isPresent()&&min.isPresent()&&max.get().getMacdValue()>0&&min.get().getMacdValue()<0){
            Double upperMACDValue0 = (max.get().getMacdValue() - 0)/5;
            for (MacdDataEntity goldenData : goldenDataList) {
                if(goldenData.getSignalValue() > 0 && goldenData.getSignalValue() < upperMACDValue0){
                    System.out.println(goldenData.getDayString() + "金叉");
                }
            }
        }

        return null;
    }

    @Override
    public List<TimeData> findDeviate(String symbol) {
        SearchFilter searchFilter = SearchFilter.getDefault();
        searchFilter.add(Restrictions.eq("intervalType", IntervalType.Day));
        searchFilter.add(Restrictions.eq("symbol",symbol));
        searchFilter.add(Order.desc("day"));
        List<MacdDataEntity> macdDataEntities = macdDataService.list(searchFilter);

        for (MacdDataEntity macdDataEntity : macdDataEntities) {
            System.out.println(macdDataEntity.getDayString() + " " + macdDataEntity.getMacdType() + " " + macdDataEntity.getMacdValue());
        }

        List<MacdDataEntity> goldenDataList = macdDataEntities.stream().filter(e -> e.getMacdType() == MacdType.GOLDEN_CROSS).collect(Collectors.toList());



        if(goldenDataList.size()>1){
            MacdDataEntity macdDataEntity_0 = goldenDataList.get(0);
            MacdDataEntity macdDataEntity_1 = goldenDataList.get(1);
            if(macdDataEntity_0.getMacdValue() < 0 && macdDataEntity_1.getMacdValue() < 0 && macdDataEntity_0.getMacdValue() > macdDataEntity_1.getMacdValue() && macdDataEntity_0.getCloseValue() < macdDataEntity_1.getCloseValue()){
                System.out.println(macdDataEntity_0.getDayString() + " " + macdDataEntity_0.getMacdValue());
                System.out.println(macdDataEntity_1.getDayString() + " " + macdDataEntity_1.getMacdValue());
            }
        }
        return null;
    }
}
