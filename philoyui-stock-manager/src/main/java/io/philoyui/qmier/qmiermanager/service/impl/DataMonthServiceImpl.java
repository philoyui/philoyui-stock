package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataMonthDao;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.DataType;
import io.philoyui.qmier.qmiermanager.service.DataDownloadInterface;
import io.philoyui.qmier.qmiermanager.service.DataMonthService;
import io.philoyui.qmier.qmiermanager.to.HistoryData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataMonthServiceImpl extends GenericServiceImpl<DataMonthEntity,Long> implements DataMonthService {

    @Autowired
    private DataMonthDao dataMonthDao;

    @Autowired
    private DataDownloader dataDownloader;

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
        dataDownloader.process(DataType.Month, new DataDownloadInterface() {
            @Override
            public void process(HistoryData[] historyDataArray, FinancialProductEntity financialProductEntity) {
                deleteBySymbol(financialProductEntity.getSymbol());
                List<DataMonthEntity> dataMonthEntityList = new ArrayList<>();
                for (HistoryData historyData : historyDataArray) {
                    DataMonthEntity dataMonthEntity = new DataMonthEntity();
                    dataMonthEntity.setSymbol(financialProductEntity.getSymbol());
                    dataMonthEntity.setDay(historyData.getDay());
                    dataMonthEntity.setDateString(DateFormatUtils.format(historyData.getDay(),"yyyy-MM-dd HH:mm:ss"));
                    dataMonthEntity.setOpen(Double.parseDouble(historyData.getOpen()));
                    dataMonthEntity.setHigh(Double.parseDouble(historyData.getHigh()));
                    dataMonthEntity.setLow(Double.parseDouble(historyData.getLow()));
                    dataMonthEntity.setClose(Double.parseDouble(historyData.getClose()));
                    dataMonthEntity.setVolume(Long.parseLong(historyData.getVolume()));
                    dataMonthEntityList.add(dataMonthEntity);
                }
                insertAll(dataMonthEntityList);
            }
        });

    }
}