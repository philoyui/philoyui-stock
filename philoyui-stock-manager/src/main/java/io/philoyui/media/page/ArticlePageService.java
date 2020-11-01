package io.philoyui.media.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.media.entity.ArticleEntity;
import io.philoyui.media.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ArticlePageService extends PageService<ArticleEntity, Long> {

    @Autowired
    private ArticleService articleService;

    @Override
    public PageObject<ArticleEntity> paged(SearchFilter searchFilter) {
        return articleService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext);
        pageConfig.withDomainName("article");
        pageConfig.withDomainClass(ArticleEntity.class);
        pageConfig.withDomainChineseName("文章");
        pageConfig.withFieldDefinitions(
                new LongFieldDefinition("id", "ID"),
                new StringFieldDefinition("title", "标题"),
                new ImageFieldDefinition("picUrl", "图片",200,200),
                new DateFieldDefinition("createdTime", "创建时间"),
                new DateFieldDefinition("modifyTime", "修改时间"),
                new EditorFieldDefinition("content", "正文")
        );
        pageConfig.withTableColumnDefinitions(
                "#checkbox_4",
                "id_6",
                "title_30",
                "picUrl_20",
                "createdTime_10",
                "modifyTime_10",
                "#operation_20"
        );
        pageConfig.withFilterDefinitions(
                "title_like"
        );
        pageConfig.withSortDefinitions("createdTime_asc","createdTime_desc","modifyTime_asc","modifyTime_desc");
        pageConfig.withTableAction(
                new CreateOperation()
        );
        pageConfig.withColumnAction(
                new EditOperation(),
                new DeleteOperation()
        );
        pageConfig.withFormItemDefinition(
                "title_rw",
                "picUrl_rw",
                "content_rw"
        );
        pageConfig.withDefaultPageSize("100");
        return pageConfig;
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
        if(articleEntity.getId()==null){
            articleEntity.setCreatedTime(new Date());
        }
        articleEntity.setModifyTime(new Date());
        articleService.update(articleEntity);
    }

    @Override
    public void delete(ArticleEntity articleEntity) {
        articleService.delete(articleEntity.getId());
    }
}
