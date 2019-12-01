package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataWeekDao;
import io.philoyui.qmier.qmiermanager.entity.DataWeekEntity;
import io.philoyui.qmier.qmiermanager.service.DataWeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DataWeekServiceImpl extends GenericServiceImpl<DataWeekEntity,Long> implements DataWeekService {

    @Autowired
    private DataWeekDao dataWeekDao;

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
}