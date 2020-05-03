package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.TaskLogEntity;

import java.util.List;

public interface TaskLogService extends GenericService<TaskLogEntity,Long> {

    /**
     * 记录标签数据
     * @param stockEntity
     * @param tagList
     */
    void logDownloadSuccess(StockEntity stockEntity, List<TagStockEntity> tagList);

}
