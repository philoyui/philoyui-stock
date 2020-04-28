package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.StockEventDao;
import io.philoyui.qmier.qmiermanager.entity.StockEventEntity;
import io.philoyui.qmier.qmiermanager.service.StockEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockEventServiceImpl extends GenericServiceImpl<StockEventEntity,Long> implements StockEventService {

    @Autowired
    private StockEventDao stockEventDao;

    @Override
    protected GenericDao<StockEventEntity, Long> getDao() {
        return stockEventDao;
    }
}
