package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataHourDao;
import io.philoyui.qmier.qmiermanager.entity.DataHourEntity;
import io.philoyui.qmier.qmiermanager.service.DataHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DataHourServiceImpl extends GenericServiceImpl<DataHourEntity,Long> implements DataHourService {

    @Autowired
    private DataHourDao dataHourDao;

    @Override
    protected GenericDao<DataHourEntity, Long> getDao() {
        return dataHourDao;
    }

    @Transactional
    @Override
    public void deleteBySymbol(String symbol) {
        dataHourDao.deleteBySymbol(symbol);
    }

    @Override
    public void insertAll(List<DataHourEntity> dataHourEntityList) {
        dataHourDao.saveAll(dataHourEntityList);
    }
}