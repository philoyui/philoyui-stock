package io.philoyui.stock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.tagstock.entity.TagEntity;

public interface TagService extends GenericService<TagEntity,Long> {

    TagEntity findByTagName(String tagName);

}
