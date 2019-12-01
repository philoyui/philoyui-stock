package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.DataDayDao;
import io.philoyui.qmier.qmiermanager.entity.DataDayEntity;
import io.philoyui.qmier.qmiermanager.service.DataDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class DataDayServiceImpl extends GenericServiceImpl<DataDayEntity,Long> implements DataDayService {

    @Autowired
    private DataDayDao dataDayDao;

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
}