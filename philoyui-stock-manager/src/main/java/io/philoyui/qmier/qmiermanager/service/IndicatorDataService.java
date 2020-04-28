package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.entity.IndicatorDataEntity;

import java.util.List;

public interface IndicatorDataService {

    /**
     * 根据股票代码和指标类型获 取指标数据
     * @param symbol            股票代码
     * @param identifier        指标唯一标识
     * @return                  指标数据
     */
    List<IndicatorDataEntity> findBySymbolAndIndicatorIdentifier(String symbol, String identifier);
}
