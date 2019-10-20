package io.philoyui.qmier.qmiermanager.dao;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import io.philoyui.qmier.qmiermanager.entity.ArticleRecordEntity;

public interface ArticleRecordDao extends GenericDao<ArticleRecordEntity,Long> {

    boolean existsBySiteCategoryIdAndDetailId(Long siteCategoryId, String detailId);

}
