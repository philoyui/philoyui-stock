package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;

public interface TagDao extends GenericDao<TagEntity,Long> {

    TagEntity findByTagName(String tagName);

}
