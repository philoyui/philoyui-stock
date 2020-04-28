package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataWeekDao;
import io.philoyui.qmier.qmiermanager.domain.StockHistoryData;
import io.philoyui.qmier.qmiermanager.entity.DataMonthEntity;
import io.philoyui.qmier.qmiermanager.entity.DataWeekEntity;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.WeekDataService;
import io.philoyui.qmier.qmiermanager.to.KLineData;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WeekDataServiceImpl extends GenericServiceImpl<DataWeekEntity,Long> implements WeekDataService {

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

    @Override
    public StockHistoryData findStockHistoryData(StockEntity stockEntity) {
        SearchFilter pagedSearchFilter = SearchFilter.getPagedSearchFilter(0, 160);
        pagedSearchFilter.add(Restrictions.eq("symbol",stockEntity.getSymbol()));
        pagedSearchFilter.add(Order.desc("day"));
        PageObject<DataWeekEntity> pageObjects = this.paged(pagedSearchFilter);

        StockHistoryData stockHistoryData = new StockHistoryData();
        stockHistoryData.setLowArray(pageObjects.getContent().stream().mapToDouble(DataWeekEntity::getLow).toArray());
        stockHistoryData.setHighArray(pageObjects.getContent().stream().mapToDouble(DataWeekEntity::getHigh).toArray());
        stockHistoryData.setCloseArray(pageObjects.getContent().stream().mapToDouble(DataWeekEntity::getClose).toArray());
        stockHistoryData.setOpenArray(pageObjects.getContent().stream().mapToDouble(DataWeekEntity::getOpen).toArray());
        stockHistoryData.setVolumeArray(pageObjects.getContent().stream().mapToDouble(DataWeekEntity::getVolume).toArray());
        stockHistoryData.setStockData(pageObjects.getContent().toArray(new DataWeekEntity[0]));
        return stockHistoryData;
    }
}