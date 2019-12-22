package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataMonthDao;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataMonthService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DataMonthServiceImpl extends GenericServiceImpl<DataMonthEntity,Long> implements DataMonthService {

    @Autowired
    private DataMonthDao dataMonthDao;

    @Autowired
    private KLineDataDownloaderImpl dataDownloaderImpl;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

    @Override
    protected GenericDao<DataMonthEntity, Long> getDao() {
        return dataMonthDao;
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        dataMonthDao.deleteBySymbol(symbol);
    }

    @Override
    public void insertAll(List<DataMonthEntity> dataMonthEntityList) {
        dataMonthDao.saveAll(dataMonthEntityList);
    }

    @Override
    public void downloadHistory() {
        dataMonthDao.deleteAll();
        taskTracerImpl.trace(TaskType.Month, taskCounter -> dataDownloaderImpl.download(TaskType.Month, (financialProductEntity, historyDataArray) -> {
            List<DataMonthEntity> dataMonthEntityList = new ArrayList<>();
            for (KLineData KLineData : historyDataArray) {
                DataMonthEntity dataMonthEntity = new DataMonthEntity();
                dataMonthEntity.setSymbol(financialProductEntity.getSymbol());
                dataMonthEntity.setName(financialProductEntity.getName());

                dataMonthEntity.setDay(KLineData.getDay());
                dataMonthEntity.setDateString(DateFormatUtils.format(KLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                dataMonthEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
                dataMonthEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
                dataMonthEntity.setLow(Double.parseDouble(KLineData.getLow()));
                dataMonthEntity.setClose(Double.parseDouble(KLineData.getClose()));
                dataMonthEntity.setVolume(Long.parseLong(KLineData.getVolume()));
                dataMonthEntity.setRecordTime(new Date());
                dataMonthEntityList.add(dataMonthEntity);
            }
            insertAll(dataMonthEntityList);
            taskCounter.increase();
        }));

    }
}