package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.ChooseDefinitionEntity;

import java.util.List;

public interface ChooseDefinitionService extends GenericService<ChooseDefinitionEntity,Long> {

    List<ChooseDefinitionEntity> findByEnable(boolean enable);

    void tagStock(ChooseDefinitionEntity chooseDefinitionEntity);

    void enable(long id);

    void disable(long id);
}
