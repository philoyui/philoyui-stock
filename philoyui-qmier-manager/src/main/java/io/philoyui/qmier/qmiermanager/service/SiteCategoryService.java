package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import cn.com.gome.page.field.domain.PageDomainProvider;
import io.philoyui.qmier.qmiermanager.entity.SiteCategoryEntity;

import java.util.List;

public interface SiteCategoryService extends GenericService<SiteCategoryEntity,Long>, PageDomainProvider<SiteCategoryEntity> {

    List<SiteCategoryEntity> findAllEnable();


}
