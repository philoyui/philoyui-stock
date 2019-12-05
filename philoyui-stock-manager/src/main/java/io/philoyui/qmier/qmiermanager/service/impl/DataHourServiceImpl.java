package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataHourDao;
import io.philoyui.qmier.qmiermanager.entity.DataHourEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.service.DataHourService;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
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
    private DataDownloader dataDownloader;

    @Autowired
    private TaskTracer taskTracer;

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
        taskTracer.trace(TaskType.Hour, taskCounter -> dataDownloader.process(TaskType.Hour, (historyDataArray, financialProductEntity) -> {
            List<DataHourEntity> dataHourEntityList = new ArrayList<>();
            for (HistoryData historyData : historyDataArray) {
                DataHourEntity dataHourEntity = new DataHourEntity();
                dataHourEntity.setSymbol(financialProductEntity.getSymbol());
                dataHourEntity.setName(financialProductEntity.getName());

                dataHourEntity.setDay(historyData.getDay());
                dataHourEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                dataHourEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                dataHourEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                dataHourEntity.setLow(Double.parseDouble(historyData.getLow()));
                dataHourEntity.setClose(Double.parseDouble(historyData.getClose()));
                dataHourEntity.setVolume(Long.parseLong(historyData.getVolume()));
                dataHourEntity.setRecordTime(new Date());
                dataHourEntityList.add(dataHourEntity);
            }
            insertAll(dataHourEntityList);
            taskCounter.increase();
        }));
    }
}