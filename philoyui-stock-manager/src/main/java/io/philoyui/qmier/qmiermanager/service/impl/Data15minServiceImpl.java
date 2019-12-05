package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.Data15minDao;
import io.philoyui.qmier.qmiermanager.entity.Data15minEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.Data15minService;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Data15minServiceImpl extends GenericServiceImpl<Data15minEntity,Long> implements Data15minService {

    @Autowired
    private Data15minDao data15minDao;

    @Autowired
    private DataDownloader dataDownloader;

    @Autowired
    private TaskTracer taskTracer;

    @Override
    protected GenericDao<Data15minEntity, Long> getDao() {
        return data15minDao;
    }

    @Override
    public void insertAll(List<Data15minEntity> data15minEntityList) {
        data15minDao.saveAll(data15minEntityList);
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        data15minDao.deleteBySymbol(symbol);
    }

    @Override
    public void downloadHistory() {
        data15minDao.deleteAll();
        taskTracer.trace(TaskType.Min_15, taskCounter -> dataDownloader.process(TaskType.Min_15, (historyDataArray, financialProductEntity) -> {
            List<Data15minEntity> data15minEntityList = new ArrayList<>();
            for (HistoryData historyData : historyDataArray) {
                Data15minEntity data15minEntity = new Data15minEntity();
                data15minEntity.setSymbol(financialProductEntity.getSymbol());
                data15minEntity.setName(financialProductEntity.getName());
                data15minEntity.setDay(historyData.getDay());
                data15minEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                data15minEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                data15minEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                data15minEntity.setLow(Double.parseDouble(historyData.getLow()));
                data15minEntity.setClose(Double.parseDouble(historyData.getClose()));
                data15minEntity.setVolume(Long.parseLong(historyData.getVolume()));
                data15minEntity.setRecordTime(new Date());
                data15minEntityList.add(data15minEntity);
            }
            insertAll(data15minEntityList);
            taskCounter.increase();
        }));

    }
}