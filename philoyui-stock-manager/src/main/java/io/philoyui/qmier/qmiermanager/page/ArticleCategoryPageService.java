package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.category.CategoryServiceProvider;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.domain.CategoryEntity;
import cn.com.gome.page.field.CategoryFieldDefinition;
import cn.com.gome.page.field.EditorFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import cn.com.gome.page.field.domain.PageDomainProvider;
import io.philoyui.qmier.qmiermanager.entity.ArticleCategoryEntity;
import io.philoyui.qmier.qmiermanager.service.ArticleCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;


@Component
public class ArticleCategoryPageService extends PageService<ArticleCategoryEntity,Long> implements CategoryServiceProvider,PageDomainProvider {

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Override
    public PageObject<ArticleCategoryEntity> paged(SearchFilter searchFilter) {
        return articleCategoryService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("article_category")
                .withDomainClass(ArticleCategoryEntity.class)
                .withDomainChineseName("文章分类")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "名字").required(),
                        new CategoryFieldDefinition("parentId", "父编码",this).required(),
                        new EditorFieldDefinition("brief", "简述")
                )
                .withFilterDefinitions("parentId")
                .withTableColumnDefinitions(
                        "id_10",
                        "name_30",
                        "parentId_20",
                        "#operation_40"
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "name_rw",
                        "parentId_rw"
                );

        return pageConfig;
    }

    @Override
    public ArticleCategoryEntity get(String id) {
        return articleCategoryService.get(Long.parseLong(id));
    }

    @Override
    public ArticleCategoryEntity get(SearchFilter searchFilter) {
        return articleCategoryService.get(searchFilter);
    }

    @Override
    public CategoryEntity getCategoryEntity(String categoryId) {
        return articleCategoryService.get(Long.parseLong(categoryId));
    }

    @Override
    public CategoryEntity[] findAllCategoryEntity() {
        return articleCategoryService.findAllEnable().toArray(new CategoryEntity[0]);
    }

    @Override
    public Serializable transformToId(String fieldValue) {
        return Long.parseLong(fieldValue);
    }

    @Override
    public boolean isRoot(String categoryId) {
        return categoryId.equalsIgnoreCase("0");
    }

    @Override
    public void saveOrUpdate(ArticleCategoryEntity articleCategoryEntity) {
        articleCategoryService.insert(articleCategoryEntity);
    }

    @Override
    public void delete(ArticleCategoryEntity articleCategoryEntity) {
        articleCategoryService.delete(articleCategoryEntity.getId());
    }

    @Override
    public Object findByReferId(String referId) {
        return articleCategoryService.get(Long.parseLong(referId));
    }

    @Override
    public String getDomainName() {
        return "article_category";
    }

    @Override
    public String getDomainChineseName() {
        return "文章分类";
    }

    @Override
    public String getDisplayFieldName() {
        return "name";
    }

    @Override
    public List findAll() {
        return articleCategoryService.findAllEnable();
    }
}
