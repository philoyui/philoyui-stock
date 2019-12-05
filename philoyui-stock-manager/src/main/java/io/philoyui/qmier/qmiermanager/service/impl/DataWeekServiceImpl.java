package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataWeekDao;
import io.philoyui.qmier.qmiermanager.entity.DataWeekEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataWeekServiceImpl extends GenericServiceImpl<DataWeekEntity,Long> implements DataWeekService {

    @Autowired
    private DataWeekDao dataWeekDao;

    @Autowired
    private DataDownloader dataDownloader;

    @Autowired
    private TaskTracer taskTracer;

    @Override
    protected GenericDao<DataWeekEntity, Long> getDao() {
        return dataWeekDao;
    }

    @Override
    public void insertAll(List<DataWeekEntity> dataWeekEntityList) {
        dataWeekDao.saveAll(dataWeekEntityList);
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        dataWeekDao.deleteBySymbol(symbol);
    }

    @Override
    public void downloadHistory() {
        dataWeekDao.deleteAll();
        taskTracer.trace(TaskType.Week, taskCounter -> dataDownloader.process(TaskType.Week, (historyDataArray, financialProductEntity) -> {
            List<DataWeekEntity> dataWeekEntityList = new ArrayList<>();
            for (HistoryData historyData : historyDataArray) {
                DataWeekEntity dataWeekEntity = new DataWeekEntity();
                dataWeekEntity.setSymbol(financialProductEntity.getSymbol());
                dataWeekEntity.setName(financialProductEntity.getName());

                dataWeekEntity.setDay(historyData.getDay());
                dataWeekEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                dataWeekEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                dataWeekEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                dataWeekEntity.setLow(Double.parseDouble(historyData.getLow()));
                dataWeekEntity.setClose(Double.parseDouble(historyData.getClose()));
                dataWeekEntity.setVolume(Long.parseLong(historyData.getVolume()));
                dataWeekEntity.setRecordTime(new Date());
                dataWeekEntityList.add(dataWeekEntity);
            }
            insertAll(dataWeekEntityList);
            taskCounter.increase();
        }));

    }
}