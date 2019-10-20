package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.dao.ArticleRecordDao;
import io.philoyui.qmier.qmiermanager.entity.ArticleRecordEntity;
import io.philoyui.qmier.qmiermanager.service.ArticleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ArticleRecordServiceImpl extends GenericServiceImpl<ArticleRecordEntity,Long> implements ArticleRecordService {

    @Autowired
    private ArticleRecordDao articleRecordDao;

    @Override
    protected GenericDao<ArticleRecordEntity, Long> getDao() {
        return articleRecordDao;
    }

    @Override
    public boolean existsBySiteCategoryIdAndDetailId(Long siteCategoryId, String detailId) {
        return articleRecordDao.existsBySiteCategoryIdAndDetailId(siteCategoryId,detailId);
    }
}
