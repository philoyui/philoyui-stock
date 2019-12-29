package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;

public interface FilterDefinitionService extends GenericService<FilterDefinitionEntity,Long> {
    void filterStock();

    void enable(Long id);

    void disable(Long id);

    void tagStock(FilterDefinitionEntity filterDefinitionEntity);
}
