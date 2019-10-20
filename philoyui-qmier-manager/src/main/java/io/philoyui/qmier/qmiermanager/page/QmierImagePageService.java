package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.BatchDeleteOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DomainLongFieldDefinition;
import cn.com.gome.page.field.ImageFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.QmierCommentEntity;
import io.philoyui.qmier.qmiermanager.entity.QmierImageEntity;
import io.philoyui.qmier.qmiermanager.service.QmierImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QmierImagePageService extends PageService<QmierImageEntity,Long>{

    @Autowired
    private QmierImageService qmierImageService;

    @Autowired
    private QmierPageService qmierPageService;

    @Autowired
    private ArticleRecordPageService articleRecordPageService;

    @Override
    public PageObject<QmierImageEntity> paged(SearchFilter searchFilter) {
        return qmierImageService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("qmier_image")
                .withDomainClass(QmierImageEntity.class)
                .withDomainChineseName("照片")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new DomainLongFieldDefinition("qmierId", "qmier",qmierPageService).required(),
                        new ImageFieldDefinition("imagePath", "照片",300,200).required(),
                        new DomainLongFieldDefinition("articleId", "文章ID",articleRecordPageService).required()
                )
                .withTableColumnDefinitions(
                        "#checkbox_5",
                        "id_5",
                        "qmierId_10",
                        "articleId_25",
                        "imagePath_45",
                        "#operation_10"
                )
                .withFilterDefinitions("qmierId")
                .withTableAction(
                        new BatchDeleteOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "qmierId_rw",
                        "articleId_rw",
                        "imagePath_rw"
                );
    }

    @Override
    public QmierImageEntity get(String id) {
        return qmierImageService.get(Long.parseLong(id));
    }

    @Override
    public QmierImageEntity get(SearchFilter searchFilter) {
        return qmierImageService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(QmierImageEntity qmierImageEntity) {
        qmierImageService.insert(qmierImageEntity);
    }

    @Override
    public void delete(QmierImageEntity qmierImageEntity) {
        qmierImageService.delete(qmierImageEntity.getId());
    }

    @Override
    public void delete(List<QmierImageEntity> entities) {
        qmierImageService.delete(entities);
    }

}
