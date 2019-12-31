package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.ButtonStyle;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.batch.TableOperation;
import cn.com.gome.page.button.column.*;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.EnableFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.FilterDefinitionEntity;
import io.philoyui.qmier.qmiermanager.service.FilterDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FilterDefinitionPageService extends PageService<FilterDefinitionEntity,Long> {

    @Autowired
    private FilterDefinitionService filterDefinitionService;

    @Override
    public PageObject<FilterDefinitionEntity> paged(SearchFilter searchFilter) {
        return filterDefinitionService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("filter_definition")
                .withDomainClass(FilterDefinitionEntity.class)
                .withDomainChineseName("筛选条件")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("identifier", "唯一标识"),
                        new StringFieldDefinition("name", "名字"),
                        new StringFieldDefinition("description", "描述"),
                        new StringFieldDefinition("param1", "参数1"),
                        new StringFieldDefinition("param2", "参数2"),
                        new StringFieldDefinition("param3", "参数3"),
                        new DateFieldDefinition("lastExecuteTime", "上次执行时间"),
                        new IntFieldDefinition("filterCount","筛选个数"),
                        new EnableFieldDefinition("enable", "是否启用")
                )
                .withTableColumnDefinitions(
                        "identifier_15",
                        "name_15",
                        "description_30",
                        "lastExecuteTime_10",
                        "filterCount_5",
                        "enable_5",
                        "#operation_20"
                )
                .withFilterDefinitions(
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation(),
                        new TableOperation("筛选股票","filterAll", ButtonStyle.Green)
                )
                .withColumnAction(
                        new ConfirmOperation("tagStock","打标"),
                        new NewPageOperation("标记股票","/admin/tag_stock/page?tagName=#name#","标记股票","name"),
                        new EnableOperation("enable"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "identifier_rw",
                        "name_rw",
                        "description_rw",
                        "enable_rw",
                        "param1_rw",
                        "param2_rw",
                        "param3_rw"
                );
        return pageConfig;
    }

    @Override
    public FilterDefinitionEntity get(String id) {
        return filterDefinitionService.get(Long.parseLong(id));
    }

    @Override
    public FilterDefinitionEntity get(SearchFilter searchFilter) {
        return filterDefinitionService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(FilterDefinitionEntity filterDefinition) {
        filterDefinitionService.insert(filterDefinition);
    }

    @Override
    public void delete(FilterDefinitionEntity filterDefinition) {
        filterDefinitionService.delete(filterDefinition.getId());
    }

    @Override
    public void enable(String id) {
        filterDefinitionService.enable(Long.parseLong(id));
    }

    @Override
    public void disable(String id) {
        filterDefinitionService.disable(Long.parseLong(id));
    }
}

