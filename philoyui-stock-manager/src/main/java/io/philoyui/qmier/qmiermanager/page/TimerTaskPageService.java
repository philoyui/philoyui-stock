package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.*;
import io.philoyui.qmier.qmiermanager.entity.TimerTaskEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;
import io.philoyui.qmier.qmiermanager.service.TimerTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimerTaskPageService extends PageService<TimerTaskEntity,Long> {

    @Autowired
    private TimerTaskService timerTaskService;

    @Override
    public PageObject<TimerTaskEntity> paged(SearchFilter searchFilter) {
        return timerTaskService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("timer_task")
                .withDomainClass(TimerTaskEntity.class)
                .withDomainChineseName("定时任务")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "任务名称"),
                        new EnumFieldDefinition("taskType", "任务类型",TaskType.class),
                        new DateFieldDefinition("startTime", "启动时间"),
                        new DateFieldDefinition("endTime", "结束时间"),
                        new EnableFieldDefinition("success", "是否成功"),
                        new StringFieldDefinition("executeResult", "执行结果"),
                        new LongFieldDefinition("completeCount", "完成个数"),
                        new LongFieldDefinition("periodMinute","持续时长(分钟)")
                )
                .withTableColumnDefinitions(
                        "name_10",
                        "taskType_10",
                        "startTime_10",
                        "endTime_10",
                        "success_10",
                        "executeResult_10",
                        "completeCount_10",
                        "periodMinute_10",
                        "#operation_10"
                )
                .withFilterDefinitions(
                )
                .withSortDefinitions(
                    "startTime_desc","startTime_asc",
                    "endTime_desc","endTime_asc"
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
                        "taskType_rw",
                        "startTime_rw",
                        "endTime_rw",
                        "success_rw",
                        "executeResult_rw",
                        "completeCount_rw"
                );
        return pageConfig;
    }

    @Override
    public TimerTaskEntity get(String id) {
        return timerTaskService.get(Long.parseLong(id));
    }

    @Override
    public TimerTaskEntity get(SearchFilter searchFilter) {
        return timerTaskService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(TimerTaskEntity timerTask) {
        timerTaskService.insert(timerTask);
    }

    @Override
    public void delete(TimerTaskEntity timerTask) {
        timerTaskService.delete(timerTask.getId());
    }
}

