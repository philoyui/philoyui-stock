package io.philoyui.qmier.qmiermanager.service;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;

import java.util.List;

public interface TaskLogService {

    /**
     * 记录标签数据
     * @param stockEntity
     * @param tagList
     */
    void logDownloadSuccess(StockEntity stockEntity, List<TagEntity> tagList);

}
