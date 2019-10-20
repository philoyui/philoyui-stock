package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.QmierEntity;

public interface QmierService extends GenericService<QmierEntity,Long> {
    QmierEntity findByCityAndName(String name, String qmierName);
}
