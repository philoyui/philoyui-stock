package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.AnnounceEntity;

public interface AnnounceService extends GenericService<AnnounceEntity,Long> {
    boolean existsByDetailUrl(String detailUrl);
}
