package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.StockYearDataDao;
import io.philoyui.qmier.qmiermanager.entity.StockYearDataEntity;
import io.philoyui.qmier.qmiermanager.service.StockYearDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockYearDataServiceImpl extends GenericServiceImpl<StockYearDataEntity,Long> implements StockYearDataService {

    @Autowired
    private StockYearDataDao stockYearDataDao;

    @Override
    protected GenericDao<StockYearDataEntity, Long> getDao() {
        return stockYearDataDao;
    }

}
