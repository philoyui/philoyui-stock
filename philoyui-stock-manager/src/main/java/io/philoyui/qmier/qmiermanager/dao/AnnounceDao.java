package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.AnnounceEntity;

public interface AnnounceDao extends GenericDao<AnnounceEntity,Long> {
    boolean existsByDetailUrl(String detailUrl);
}