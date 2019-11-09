package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DoubleFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import ${basePath}.entity.${EntityName}Entity;
import ${basePath}.service.${EntityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ${EntityName}PageService extends PageService<${EntityName}Entity,Long> {

    @Autowired
    private ${EntityName}Service ${entityName}Service;

    @Override
    public PageObject<${EntityName}Entity> paged(SearchFilter searchFilter) {
        return ${entityName}Service.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        return new PageConfig(pageContext)
                .withDomainName("${entityName}")
                .withDomainClass(${EntityName}Entity.class)
                .withDomainChineseName("######")
                .withFieldDefinitions(
                    <#list fieldType as fieldTypes>
                        new ${fieldType.fieldType}FieldDefinition("${fieldType.fieldName}", "${fieldType.chineseName}").required(),
                    </#list>
                )
                .withTableColumnDefinitions(
                    <#list fieldType as fieldTypes>
                        "${fieldType.fieldName}_10",
                    </#list>
                        "#operation_10"
                )
                .withFilterDefinitions(
                    <#list fieldType as fieldTypes>
                        "${fieldType.fieldName}",
                    </#list>
                )
                .withSortDefinitions(
                    <#list fieldType as fieldTypes>
                        "${fieldType.fieldName}_asc",
                        "${fieldType.fieldName}_desc",
                    </#list>
                        )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                    <#list fieldType as fieldTypes>
                        "${fieldType.fieldName}_rw",
                    </#list>
                );
    }

    @Override
    public ${EntityName}Entity get(String id) {
        return ${entityName}Service.get(Long.parseLong(id));
    }

    @Override
    public ${EntityName}Entity get(SearchFilter searchFilter) {
        return ${entityName}Service.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(${EntityName}Entity ${entityName}Entity) {
        ${entityName}Service.insert(${entityName}Entity);
    }

    @Override
    public void delete(${EntityName}Entity ${entityName}Entity) {
        ${entityName}Service.delete(${entityName}Entity.getId());
    }

    @Override
    public void delete(List<${EntityName}Entity> entities) {
        ${entityName}Service.delete(entities);
    }
}
