package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataWeekDao;
import io.philoyui.qmier.qmiermanager.entity.DataWeekEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
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
    private KLineDataDownloaderImpl dataDownloaderImpl;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

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
        taskTracerImpl.trace(TaskType.Week, taskCounter -> dataDownloaderImpl.download(TaskType.Week, (financialProductEntity, historyDataArray) -> {
            List<DataWeekEntity> dataWeekEntityList = new ArrayList<>();
            for (KLineData KLineData : historyDataArray) {
                DataWeekEntity dataWeekEntity = new DataWeekEntity();
                dataWeekEntity.setSymbol(financialProductEntity.getSymbol());
                dataWeekEntity.setName(financialProductEntity.getName());

                dataWeekEntity.setDay(KLineData.getDay());
                dataWeekEntity.setDateString(DateFormatUtils.format(KLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                dataWeekEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
                dataWeekEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
                dataWeekEntity.setLow(Double.parseDouble(KLineData.getLow()));
                dataWeekEntity.setClose(Double.parseDouble(KLineData.getClose()));
                dataWeekEntity.setVolume(Long.parseLong(KLineData.getVolume()));
                dataWeekEntity.setRecordTime(new Date());
                dataWeekEntityList.add(dataWeekEntity);
            }
            insertAll(dataWeekEntityList);
            taskCounter.increase();
        }));

    }
}