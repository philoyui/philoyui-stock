package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.Data30minDao;
import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.Data30minService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class Data30minServiceImpl extends GenericServiceImpl<Data30minEntity,Long> implements Data30minService {

    @Autowired
    private Data30minDao data30minDao;

    @Autowired
    private KLineDataDownloaderImpl dataDownloaderImpl;

    @Autowired
    private TaskTracerImpl taskTracerImpl;

    @Override
    protected GenericDao<Data30minEntity, Long> getDao() {
        return data30minDao;
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        data30minDao.deleteBySymbol(symbol);
    }

    @Override
    public void insertAll(List<Data30minEntity> data30minEntityList) {
        data30minDao.saveAll(data30minEntityList);
    }

    @Override
    public void downloadHistory() {
        data30minDao.deleteAll();
        taskTracerImpl.trace(TaskType.Min_30, taskCounter -> dataDownloaderImpl.download(TaskType.Min_30, (financialProductEntity, kLineDataArray) -> {
            List<Data30minEntity> data30minEntityList = new ArrayList<>();
            for (KLineData kLineData : kLineDataArray) {
                Data30minEntity data30minEntity = new Data30minEntity();
                data30minEntity.setSymbol(financialProductEntity.getSymbol());
                data30minEntity.setName(financialProductEntity.getName());
                data30minEntity.setDay(kLineData.getDay());
                data30minEntity.setDateString(DateFormatUtils.format(kLineData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                data30minEntity.setOpen(Double.parseDouble(kLineData.getOpen()));
                data30minEntity.setHigh(Double.parseDouble(kLineData.getHigh()));
                data30minEntity.setLow(Double.parseDouble(kLineData.getLow()));
                data30minEntity.setClose(Double.parseDouble(kLineData.getClose()));
                data30minEntity.setVolume(Long.parseLong(kLineData.getVolume()));
                data30minEntity.setRecordTime(new Date());
                data30minEntityList.add(data30minEntity);
            }
            insertAll(data30minEntityList);
            taskCounter.increase();
        }));
    }
}