package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.Data15minDao;
import io.philoyui.qmier.qmiermanager.entity.Data15minEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.Data15minService;
import io.philoyui.qmier.qmiermanager.service.KLineDataDownloader;
import io.philoyui.qmier.qmiermanager.service.TaskTracer;
import io.philoyui.qmier.qmiermanager.to.KLineData;
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
    private KLineDataDownloader kLineDataDownloader;

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
        taskTracer.trace(TaskType.Min_15, taskCounter -> kLineDataDownloader.download(TaskType.Min_15, (financialProduct, kLineDataArray) -> {
            List<Data15minEntity> data15minEntityList = new ArrayList<>();
            for (KLineData KLineData : kLineDataArray) {
                Data15minEntity data15minEntity = new Data15minEntity();
                data15minEntity.setSymbol(financialProduct.getSymbol());
                data15minEntity.setName(financialProduct.getName());
                data15minEntity.setDay(KLineData.getDay());
                data15minEntity.setDateString(DateFormatUtils.format(KLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                data15minEntity.setOpen(Double.parseDouble(KLineData.getOpen()));
                data15minEntity.setHigh(Double.parseDouble(KLineData.getHigh()));
                data15minEntity.setLow(Double.parseDouble(KLineData.getLow()));
                data15minEntity.setClose(Double.parseDouble(KLineData.getClose()));
                data15minEntity.setVolume(Long.parseLong(KLineData.getVolume()));
                data15minEntity.setRecordTime(new Date());
                data15minEntityList.add(data15minEntity);
            }
            insertAll(data15minEntityList);
            taskCounter.increase();
        }));

    }
}