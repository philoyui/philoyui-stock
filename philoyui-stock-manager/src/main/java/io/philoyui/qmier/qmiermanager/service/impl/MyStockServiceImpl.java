package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.MyStockDao;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;
import io.philoyui.qmier.qmiermanager.service.MyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyStockServiceImpl extends GenericServiceImpl<MyStockEntity,Long> implements MyStockService {

    @Autowired
    private MyStockDao myStockDao;

    @Override
    protected GenericDao<MyStockEntity, Long> getDao() {
        return myStockDao;
    }

}
