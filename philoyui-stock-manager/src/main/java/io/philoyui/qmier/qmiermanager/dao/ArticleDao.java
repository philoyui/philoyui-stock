package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.ArticleEntity;

public interface ArticleDao extends GenericDao<ArticleEntity,Long> {
    boolean existsBySiteIdentifierAndDetailUrl(String siteIdentifier, String detailUrl);
}
