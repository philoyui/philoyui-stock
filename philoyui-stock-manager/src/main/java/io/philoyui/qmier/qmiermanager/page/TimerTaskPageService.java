package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.ConfirmOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DateFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.TimerTaskEntity;
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
                .withDomainChineseName("定时器")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("name", "名字"),
                        new StringFieldDefinition("cron", "CRON表达式"),
                        new StringFieldDefinition("beanName", "BeanName"),
                        new DateFieldDefinition("lastExecuteTime", "上次执行时间")
                )
                .withTableColumnDefinitions(
                        "name_20",
                        "beanName_10",
                        "cron_20",
                        "lastExecuteTime_20",
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
                        new ConfirmOperation("execute","执行"),
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "name_rw",
                        "cron_rw",
                        "beanName_rw"
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

