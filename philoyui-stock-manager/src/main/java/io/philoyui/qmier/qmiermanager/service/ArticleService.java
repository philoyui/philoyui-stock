package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.ArticleEntity;

public interface ArticleService extends GenericService<ArticleEntity,Long>{

    boolean existsBySiteIdentifierAndDetailUrl(String siteIdentifier, String detailUrl);

}
