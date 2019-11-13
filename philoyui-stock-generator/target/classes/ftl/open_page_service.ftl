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
import ${basePackage}.vo.${EntityName};
import ${basePackage}.dubbo.${EntityName}DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ${EntityName}PageService extends PageService<${EntityName},Long> {

    @Reference(group = "${appName}")
    private ${EntityName}DubboService ${entityName}DubboService;

    @Override
    public PageObject<${EntityName}> paged(SearchFilter searchFilter) {
        return ${entityName}DubboService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("${entityName}")
                .withDomainClass(${EntityName}.class)
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
    public ${EntityName} get(String id) {
        return ${entityName}DubboService.get(Long.parseLong(id));
    }

    @Override
    public ${EntityName} get(SearchFilter searchFilter) {
        return ${entityName}DubboService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(${EntityName} ${entityName}) {
        ${entityName}DubboService.saveOrUpdate(${entityName});
    }

    @Override
    public void delete(${EntityName} ${entityName}) {
        ${entityName}DubboService.delete(${entityName}.getId());
    }
}