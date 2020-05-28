package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.MacdDataDao;
import io.philoyui.qmier.qmiermanager.entity.indicator.MacdDataEntity;
import io.philoyui.qmier.qmiermanager.service.MacdDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class MacdDataServiceImpl extends GenericServiceImpl<MacdDataEntity,Long> implements MacdDataService {

    @Autowired
    private MacdDataDao macdDataDao;

    @Override
    protected GenericDao<MacdDataEntity, Long> getDao() {
        return macdDataDao;
    }

    @Override
    public List<MacdDataEntity> findBySymbol(String symbol) {
        return macdDataDao.findBySymbol(symbol);
    }

    @Transactional
    @Override
    public void deleteDayData() {
        macdDataDao.deleteData("Day");
    }

    @Transactional
    @Override
    public void deleteWeekData() {
        macdDataDao.deleteData("Week");
    }

    @Override
    public void deleteMonthData() {
        macdDataDao.deleteData("Month");
    }

}
