package io.philoyui.tagstock.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.tagstock.entity.TagEntity;

public interface TagDao extends GenericDao<TagEntity,Long> {

    TagEntity findByTagName(String tagName);

}
