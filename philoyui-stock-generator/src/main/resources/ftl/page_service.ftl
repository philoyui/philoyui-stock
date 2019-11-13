package ${basePackage}.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import com.alibaba.dubbo.config.annotation.Reference;
import ${basePackage}.entity.${EntityName}Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ${basePackage}.service.${EntityName}Service;

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
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("${domainName}")
                .withDomainClass(${EntityName}Entity.class)
                .withDomainChineseName("${EntityChineseName}")
                .withFieldDefinitions(
                    <#list fieldConfigs as fieldConfig>
                        new ${fieldConfig.type}FieldDefinition("${fieldConfig.name}", "${fieldConfig.desc}")<#if fieldConfig_has_next>,</#if>
                    </#list>
                )
                .withTableColumnDefinitions(
                    <#list fieldConfigs as fieldConfig>
                        "${fieldConfig.name}_10",
                    </#list>
                        "#operation_10"
                )
                .withFilterDefinitions(
                <#list filterFieldConfigs as fieldConfig>
                    "${fieldConfig.name}"<#if fieldConfig_has_next>,</#if>
                </#list>
                )
                .withSortDefinitions(
                <#list orderFieldConfigs as fieldConfig>
                    "${fieldConfig.name}_desc","${fieldConfig.name}_asc"<#if fieldConfig_has_next>,</#if>
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
                    <#list fieldConfigs as fieldConfig>
                        "${fieldConfig.name}_r<#if fieldConfig.writable>w</#if>"<#if fieldConfig_has_next>,</#if>
                    </#list>
                );
        return pageConfig;
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
    public void saveOrUpdate(${EntityName}Entity ${entityName}) {
        ${entityName}Service.insert(${entityName});
    }

    @Override
    public void delete(${EntityName}Entity ${entityName}) {
        ${entityName}Service.delete(${entityName}.getId());
    }
}

