package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.QmierEntity;

public interface QmierDao extends GenericDao<QmierEntity,Long> {
    QmierEntity findByCityAndName(String city, String name);
}
