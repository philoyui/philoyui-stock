package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.ArticleDao;
import io.philoyui.qmier.qmiermanager.entity.ArticleEntity;
import io.philoyui.qmier.qmiermanager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleServiceImpl extends GenericServiceImpl<ArticleEntity,Long> implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    protected GenericDao<ArticleEntity, Long> getDao() {
        return articleDao;
    }

    @Override
    public boolean existsBySiteIdentifierAndDetailUrl(String siteIdentifier, String detailUrl) {
        return articleDao.existsBySiteIdentifierAndDetailUrl(siteIdentifier,detailUrl);
    }
}
