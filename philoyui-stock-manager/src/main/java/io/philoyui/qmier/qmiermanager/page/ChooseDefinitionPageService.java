package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.*;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.EnableFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import cn.com.gome.page.field.validator.IntFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.ChooseDefinitionEntity;
import io.philoyui.qmier.qmiermanager.service.ChooseDefinitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChooseDefinitionPageService extends PageService<ChooseDefinitionEntity,Long> {

    @Autowired
    private ChooseDefinitionService chooseDefinitionService;

    @Override
    public PageObject<ChooseDefinitionEntity> paged(SearchFilter searchFilter) {
        return chooseDefinitionService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("choose_definition")
                .withDomainClass(ChooseDefinitionEntity.class)
                .withDomainChineseName("选股定义")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("identifier", "唯一标识"),
                        new StringFieldDefinition("name", "名字"),
                        new StringFieldDefinition("description", "描述"),
                        new StringFieldDefinition("param1", "参数1"),
                        new StringFieldDefinition("param2", "参数2"),
                        new StringFieldDefinition("param3", "参数3"),
                        new DateFieldDefinition("lastExecuteTime", "上次执行时间"),
                        new IntFieldDefinition("chooseCount","选股个数"),
                        new EnableFieldDefinition("enable", "是否启用")
                )
                .withTableColumnDefinitions(
                        "identifier_15",
                        "name_15",
                        "description_30",
                        "lastExecuteTime_10",
                        "chooseCount_5",
                        "enable_5",
                        "#operation_20"
                )
                .withFilterDefinitions(
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation()
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
    public ChooseDefinitionEntity get(String id) {
        return chooseDefinitionService.get(Long.parseLong(id));
    }

    @Override
    public ChooseDefinitionEntity get(SearchFilter searchFilter) {
        return chooseDefinitionService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(ChooseDefinitionEntity chooseDefinition) {
        chooseDefinitionService.insert(chooseDefinition);
    }

    @Override
    public void delete(ChooseDefinitionEntity chooseDefinition) {
        chooseDefinitionService.delete(chooseDefinition.getId());
    }

    @Override
    public void enable(String id) {
        chooseDefinitionService.enable(Long.parseLong(id));
    }

    @Override
    public void disable(String id) {
        chooseDefinitionService.disable(Long.parseLong(id));
    }
}

