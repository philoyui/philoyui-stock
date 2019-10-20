package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.QmierDao;
import io.philoyui.qmier.qmiermanager.entity.QmierEntity;
import io.philoyui.qmier.qmiermanager.service.QmierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QmierServiceImpl extends GenericServiceImpl<QmierEntity,Long> implements QmierService {

    @Autowired
    private QmierDao qmierDao;

    @Override
    protected GenericDao<QmierEntity, Long> getDao() {
        return qmierDao;
    }

    @Override
    public QmierEntity findByCityAndName(String city, String qmierName) {
        return qmierDao.findByCityAndName(city,qmierName);
    }
}
