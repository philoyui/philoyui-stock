package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.Data30minDao;
import io.philoyui.qmier.qmiermanager.entity.Data30minEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.Data30minService;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class Data30minServiceImpl extends GenericServiceImpl<Data30minEntity,Long> implements Data30minService {

    @Autowired
    private Data30minDao data30minDao;

    @Autowired
    private DataDownloader dataDownloader;

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
        dataDownloader.process(DataType.Min_30, new DataDownloadInterface() {
            @Override
            public void process(HistoryData[] historyDataArray, FinancialProductEntity financialProductEntity) {
                deleteBySymbol(financialProductEntity.getSymbol());
                List<Data30minEntity> data30minEntityList = new ArrayList<>();
                for (HistoryData historyData : historyDataArray) {
                    Data30minEntity data30minEntity = new Data30minEntity();
                    data30minEntity.setSymbol(financialProductEntity.getSymbol());
                    data30minEntity.setDay(historyData.getDay());
                    data30minEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    data30minEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                    data30minEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                    data30minEntity.setLow(Double.parseDouble(historyData.getLow()));
                    data30minEntity.setClose(Double.parseDouble(historyData.getClose()));
                    data30minEntity.setVolume(Long.parseLong(historyData.getVolume()));
                    data30minEntityList.add(data30minEntity);
                }
                insertAll(data30minEntityList);
            }
        });
    }
}