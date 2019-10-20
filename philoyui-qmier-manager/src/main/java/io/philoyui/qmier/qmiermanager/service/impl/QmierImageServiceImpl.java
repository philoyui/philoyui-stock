package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.QmierImageDao;
import io.philoyui.qmier.qmiermanager.entity.QmierImageEntity;
import io.philoyui.qmier.qmiermanager.service.QmierImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QmierImageServiceImpl extends GenericServiceImpl<QmierImageEntity,Long> implements QmierImageService {

    @Autowired
    private QmierImageDao qmierImageDao;

    @Override
    protected GenericDao<QmierImageEntity, Long> getDao() {
        return qmierImageDao;
    }

}
