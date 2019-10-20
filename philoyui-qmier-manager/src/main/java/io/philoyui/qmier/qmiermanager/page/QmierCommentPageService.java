package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.BatchDeleteOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.qmier.qmiermanager.entity.QmierCommentEntity;
import io.philoyui.qmier.qmiermanager.entity.QmierImageEntity;
import io.philoyui.qmier.qmiermanager.service.QmierCommentService;
import io.philoyui.qmier.qmiermanager.service.QmierImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QmierCommentPageService extends PageService<QmierCommentEntity,Long>{

    @Autowired
    private QmierCommentService qmierCommentService;

    @Autowired
    private QmierPageService qmierPageService;

    @Autowired
    private ArticleRecordPageService articleRecordPageService;

    @Override
    public PageObject<QmierCommentEntity> paged(SearchFilter searchFilter) {
        return qmierCommentService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("qmier_comment")
                .withDomainClass(QmierCommentEntity.class)
                .withDomainChineseName("评论")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new DomainLongFieldDefinition("qmierId", "qmier",qmierPageService).required(),
                        new TextAreaFieldDefinition("content", "评论").required(),
                        new DomainLongFieldDefinition("articleId", "文章ID",articleRecordPageService).required()
                )
                .withTableColumnDefinitions(
                        "#checkbox_5",
                        "id_5",
                        "qmierId_10",
                        "articleId_25",
                        "content_40",
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
                        "content_rw"
                );
    }

    @Override
    public QmierCommentEntity get(String id) {
        return qmierCommentService.get(Long.parseLong(id));
    }

    @Override
    public QmierCommentEntity get(SearchFilter searchFilter) {
        return qmierCommentService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(QmierCommentEntity qmierCommentEntity) {
        qmierCommentService.insert(qmierCommentEntity);
    }

    @Override
    public void delete(QmierCommentEntity qmierCommentEntity) {
        qmierCommentService.delete(qmierCommentEntity.getId());
    }

    @Override
    public void delete(List<QmierCommentEntity> entities) {
        qmierCommentService.delete(entities);
    }
}
