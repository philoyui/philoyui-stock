package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.AnnounceDao;
import io.philoyui.qmier.qmiermanager.entity.AnnounceEntity;
import io.philoyui.qmier.qmiermanager.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AnnounceServiceImpl extends GenericServiceImpl<AnnounceEntity,Long> implements AnnounceService {

    @Autowired
    private AnnounceDao announceDao;

    @Override
    protected GenericDao<AnnounceEntity, Long> getDao() {
        return announceDao;
    }

    @Override
    public boolean existsByDetailUrl(String detailUrl) {
        return announceDao.existsByDetailUrl(detailUrl);
    }
}