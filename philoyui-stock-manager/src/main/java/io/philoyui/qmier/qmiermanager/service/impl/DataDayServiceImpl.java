package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataDayDao;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
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
    private KLineDataDownloaderImpl dataDownloaderImpl;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

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
        taskTracerImpl.trace(TaskType.Day, taskCounter -> dataDownloaderImpl.download(TaskType.Day, (financialProductEntity, kLineDataArray) -> {
            List<DataDayEntity> dataDayEntityList = new ArrayList<>();
            for (KLineData KLineData : kLineDataArray) {
                DataDayEntity dataDayEntity = new DataDayEntity();
                dataDayEntity.setSymbol(financialProductEntity.getSymbol());
                dataDayEntity.setName(financialProductEntity.getName());
                dataDayEntity.setDay(KLineData.getDay());
                dataDayEntity.setDateString(DateFormatUtils.format(KLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                dataDayEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
                dataDayEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
                dataDayEntity.setLow(Double.parseDouble(KLineData.getLow()));
                dataDayEntity.setClose(Double.parseDouble(KLineData.getClose()));
                dataDayEntity.setVolume(Long.parseLong(KLineData.getVolume()));
                dataDayEntity.setRecordTime(new Date());
                dataDayEntityList.add(dataDayEntity);
            }
            insertAll(dataDayEntityList);
            taskCounter.increase();
        }));
    }
}