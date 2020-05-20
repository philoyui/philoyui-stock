package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.dao.TaskLogDao;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.TagStockEntity;
import io.philoyui.qmier.qmiermanager.entity.TaskLogEntity;
import io.philoyui.qmier.qmiermanager.service.TaskLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class TaskLogServiceImpl extends GenericServiceImpl<TaskLogEntity,Long> implements TaskLogService {

    @Autowired
    private TaskLogDao taskLogDao;

    private Gson gson = new GsonBuilder().create();

    @Override
    protected GenericDao<TaskLogEntity, Long> getDao() {
        return taskLogDao;
    }

    @Override
    public void logDownloadSuccess(StockEntity stockEntity, List<TagStockEntity> tagList) {
        TaskLogEntity taskLogEntity = new TaskLogEntity();
        taskLogEntity.setSymbol(stockEntity.getSymbol());
        taskLogEntity.setCreateTime(new Date());
        this.insert(taskLogEntity);
    }

}
