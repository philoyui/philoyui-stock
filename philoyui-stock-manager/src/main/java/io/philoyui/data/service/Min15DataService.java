package io.philoyui.data.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.data.entity.Min15DataEntity;

import java.util.List;

/**
 * @author DELL
 */
public interface Min15DataService extends GenericService<Min15DataEntity,Long> {
    void insertAll(List<Min15DataEntity> min15DataEntityList);

    void deleteAll();
}
