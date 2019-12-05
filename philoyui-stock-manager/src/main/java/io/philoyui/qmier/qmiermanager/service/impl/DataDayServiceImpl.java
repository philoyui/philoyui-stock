package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataDayDao;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataDayServiceImpl extends GenericServiceImpl<DataDayEntity,Long> implements DataDayService {

    @Autowired
    private DataDayDao dataDayDao;

    @Autowired
    private DataDownloader dataDownloader;

    @Autowired
    private TaskTracer taskTracer;

    @Override
    protected GenericDao<DataDayEntity, Long> getDao() {
        return dataDayDao;
    }

    @Override
    public void insertAll(List<DataDayEntity> dataDayEntityList) {
        dataDayDao.saveAll(dataDayEntityList);
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        dataDayDao.deleteBySymbol(symbol);
    }

    @Override
    public void downloadHistory() {
        dataDayDao.deleteAll();
        taskTracer.trace(TaskType.Day, taskCounter -> dataDownloader.process(TaskType.Day, (historyDataArray, financialProductEntity) -> {
            List<DataDayEntity> dataDayEntityList = new ArrayList<>();
            for (HistoryData historyData : historyDataArray) {
                DataDayEntity dataDayEntity = new DataDayEntity();
                dataDayEntity.setSymbol(financialProductEntity.getSymbol());
                dataDayEntity.setDay(historyData.getDay());
                dataDayEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                dataDayEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                dataDayEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                dataDayEntity.setLow(Double.parseDouble(historyData.getLow()));
                dataDayEntity.setClose(Double.parseDouble(historyData.getClose()));
                dataDayEntity.setVolume(Long.parseLong(historyData.getVolume()));
                dataDayEntity.setRecordTime(new Date());
                dataDayEntityList.add(dataDayEntity);
            }
            insertAll(dataDayEntityList);
            taskCounter.increase();
        }));
    }
}