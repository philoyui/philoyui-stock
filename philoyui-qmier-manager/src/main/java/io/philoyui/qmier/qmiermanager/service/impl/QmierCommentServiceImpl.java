package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.QmierCommentDao;
import io.philoyui.qmier.qmiermanager.entity.QmierCommentEntity;
import io.philoyui.qmier.qmiermanager.service.QmierCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QmierCommentServiceImpl extends GenericServiceImpl<QmierCommentEntity,Long> implements QmierCommentService {

    @Autowired
    private QmierCommentDao qmierCommentDao;

    @Override
    protected GenericDao<QmierCommentEntity, Long> getDao() {
        return qmierCommentDao;
    }

}
