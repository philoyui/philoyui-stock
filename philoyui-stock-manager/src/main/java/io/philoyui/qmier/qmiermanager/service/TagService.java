package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.TagEntity;

public interface TagService extends GenericService<TagEntity,Long> {
    TagEntity findByTagName(String tagName);
}
