package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.MyStockEntity;

public interface MyStockService extends GenericService<MyStockEntity,Long> {

    /**
     * 每天获取自选股数据
     */
    void obtainEveryDay();

    void deleteAll();

    /**
     * 加入关注列表
     * @param id
     */
    void addFocus(Long id);
}
