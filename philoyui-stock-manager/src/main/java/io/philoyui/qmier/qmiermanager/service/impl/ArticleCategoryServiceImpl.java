package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.ArticleCategoryDao;
import io.philoyui.qmier.qmiermanager.entity.ArticleCategoryEntity;
import io.philoyui.qmier.qmiermanager.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleCategoryServiceImpl extends GenericServiceImpl<ArticleCategoryEntity,Long> implements ArticleCategoryService {

    @Autowired
    private ArticleCategoryDao articleCategoryDao;

    @Override
    protected GenericDao<ArticleCategoryEntity, Long> getDao() {
        return articleCategoryDao;
    }

    @Override
    public List<ArticleCategoryEntity> findAllEnable() {
        SearchFilter searchFilter = SearchFilter.getDefault();
        return this.list(searchFilter);
    }
}
