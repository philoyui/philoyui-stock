package io.philoyui.qmier.qmiermanager.service;

import cn.com.gome.cloud.openplatform.service.GenericService;
import io.philoyui.qmier.qmiermanager.entity.ArticleRecordEntity;

public interface ArticleRecordService extends GenericService<ArticleRecordEntity,Long> {

    boolean existsBySiteCategoryIdAndDetailId(Long siteCategoryId, String detailId);

}
