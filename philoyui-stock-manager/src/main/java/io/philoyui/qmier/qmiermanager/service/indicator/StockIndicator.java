package io.philoyui.qmier.qmiermanager.service.indicator;

import io.philoyui.qmier.qmiermanager.entity.IndicatorDataEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;

import java.util.List;

public interface StockIndicator {

    /**
     * 获取python脚本名称
     * @return      python脚本名称
     */
    String getPythonName();

    /**
     * 指标的唯一标识
     * @return      指标的唯一标识
     */
    String getIdentifier();

    /**
     * 根据指标数据，解析标签数据
     * @param indicatorDataEntityList
     * @param stockEntity
     * @return
     */
    List<TagEntity> parseAndRecordTags(StockEntity indicatorDataEntityList, StockIndicator stockEntity);
}
