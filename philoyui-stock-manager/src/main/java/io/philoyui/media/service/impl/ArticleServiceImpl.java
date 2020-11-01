package io.philoyui.media.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.media.dao.ArticleDao;
import io.philoyui.media.entity.ArticleEntity;
import io.philoyui.media.service.ArticleService;
import org.checkerframework.checker.units.qual.A;
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

}
