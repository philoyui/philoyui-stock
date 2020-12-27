package io.philoyui.mystock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.mystock.entity.HourDataEntity;

import java.util.List;

/**
 * @author DELL
 */
public interface HourDataService extends GenericService<HourDataEntity,Long> {
    void insertAll(List<HourDataEntity> hourDataEntityList);

    void deleteAll();
}
