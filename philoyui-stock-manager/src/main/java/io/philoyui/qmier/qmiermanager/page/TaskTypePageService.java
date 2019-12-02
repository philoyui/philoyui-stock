package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.TaskTypeEntity;
import io.philoyui.qmier.qmiermanager.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskTypePageService extends PageService<TaskTypeEntity,Long> {

    @Autowired
    private TaskTypeService taskTypeService;

    @Override
    public PageObject<TaskTypeEntity> paged(SearchFilter searchFilter) {
        return taskTypeService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("task_type")
                .withDomainClass(TaskTypeEntity.class)
                .withDomainChineseName("任务类型")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "任务名称"),
                        new StringFieldDefinition("cronString", "Cron表达式"),
                        new StringFieldDefinition("description", "描述"),
                        new DateFieldDefinition("lastExecuteTime", "上次执行时间")
                )
                .withTableColumnDefinitions(
                        "id_10",
                        "name_10",
                        "cronString_10",
                        "description_10",
                        "lastExecuteTime_10",
                        "#operation_10"
                )
                .withFilterDefinitions(
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "id_rw",
                        "name_rw",
                        "cronString_rw",
                        "description_rw",
                        "lastExecuteTime_rw"
                );
        return pageConfig;
    }

    @Override
    public TaskTypeEntity get(String id) {
        return taskTypeService.get(Long.parseLong(id));
    }

    @Override
    public TaskTypeEntity get(SearchFilter searchFilter) {
        return taskTypeService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(TaskTypeEntity taskType) {
        taskTypeService.insert(taskType);
    }

    @Override
    public void delete(TaskTypeEntity taskType) {
        taskTypeService.delete(taskType.getId());
    }
}

