package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.Order;
import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.BatchActionOperation;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.*;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.ArticleEntity;
import io.philoyui.qmier.qmiermanager.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ArticlePageService extends PageService<ArticleEntity,Long> {

    @Autowired
    private ArticleService articleService;

    @Override
    public PageObject<ArticleEntity> paged(SearchFilter searchFilter) {
        searchFilter.add(Order.desc("createdTime"));
        return articleService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("article")
                .withDomainClass(ArticleEntity.class)
                .withDomainChineseName("文章")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new DisplayStringFieldDefinition("title", "标题").required(),
                        new StringFieldDefinition("siteIdentifier", "网站标识").required(),
                        new EditorFieldDefinition("content", "描述").required(),
                        new StringFieldDefinition("analyst", "分析师").required(),
                        new StringFieldDefinition("prediction", "预测").required(),
                        new IntFieldDefinition("score", "得分").required(),
                        new DateFieldDefinition("createdTime", "创建时间").required()

                )
                .withTableColumnDefinitions(
                        "#checkbox_3",
                        "title_35",
                        "analyst_5",
                        "prediction_20",
                        "createdTime_12",
                        "#operation_15"
                )
                .withFilterDefinitions("createdTime","analyst","siteIdentifier")
                .withTableAction(
                        new CreateOperation(),
                        new BatchActionOperation("分析","analysis", ButtonStyle.Orange)
                )
                .withColumnAction(
                        new NewPageOperation("详情","/admin/article/detailContent?id=#id#","详情","id"),
                        new ConfirmOperation("generateVoice","生成声音"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "title_rw",
                        "analyst_rw",
                        "content_rw",
                        "prediction_rw",
                        "score_rw"

                );
    }

    @Override
    public ArticleEntity get(String id) {
        return articleService.get(Long.parseLong(id));
    }

    @Override
    public ArticleEntity get(SearchFilter searchFilter) {
        return articleService.get(searchFilter);
    }


    @Override
    public void saveOrUpdate(ArticleEntity articleEntity) {
        articleEntity.setCreatedTime(new Date());
        articleService.insert(articleEntity);
    }

    @Override
    public void delete(ArticleEntity articleEntity) {
        articleService.delete(articleEntity.getId());
    }
}
