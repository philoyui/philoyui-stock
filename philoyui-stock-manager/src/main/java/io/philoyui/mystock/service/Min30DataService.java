package io.philoyui.mystock.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.mystock.entity.Min30DataEntity;

import java.util.List;

public interface Min30DataService extends GenericService<Min30DataEntity,Long>{

    void insertAll(List<Min30DataEntity> min30DataEntityList);

    void deleteAll();
}
