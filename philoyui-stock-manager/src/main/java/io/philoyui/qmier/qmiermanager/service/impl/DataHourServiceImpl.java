package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataHourDao;
import io.philoyui.qmier.qmiermanager.entity.DataHourEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataHourService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataHourServiceImpl extends GenericServiceImpl<DataHourEntity,Long> implements DataHourService {

    @Autowired
    private DataHourDao dataHourDao;

    @Autowired
    private KLineDataDownloaderImpl dataDownloaderImpl;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

    @Override
    protected GenericDao<DataHourEntity, Long> getDao() {
        return dataHourDao;
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        dataHourDao.deleteBySymbol(symbol);
    }

    @Override
    public void insertAll(List<DataHourEntity> dataHourEntityList) {
        dataHourDao.saveAll(dataHourEntityList);
    }

    @Override
    public void downloadHistory() {
        dataHourDao.deleteAll();
        taskTracerImpl.trace(TaskType.Hour, taskCounter -> dataDownloaderImpl.download(TaskType.Hour, (financialProductEntity, historyDataArray) -> {
            List<DataHourEntity> dataHourEntityList = new ArrayList<>();
            for (KLineData KLineData : historyDataArray) {
                DataHourEntity dataHourEntity = new DataHourEntity();
                dataHourEntity.setSymbol(financialProductEntity.getSymbol());
                dataHourEntity.setName(financialProductEntity.getName());

                dataHourEntity.setDay(KLineData.getDay());
                dataHourEntity.setDateString(DateFormatUtils.format(KLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                dataHourEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
                dataHourEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
                dataHourEntity.setLow(Double.parseDouble(KLineData.getLow()));
                dataHourEntity.setClose(Double.parseDouble(KLineData.getClose()));
                dataHourEntity.setVolume(Long.parseLong(KLineData.getVolume()));
                dataHourEntity.setRecordTime(new Date());
                dataHourEntityList.add(dataHourEntity);
            }
            insertAll(dataHourEntityList);
            taskCounter.increase();
        }));
    }
}