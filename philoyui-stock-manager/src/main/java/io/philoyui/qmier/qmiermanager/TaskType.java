package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.util.Date;

@DescEntity(name = "任务类型", domainName = "task_type")
public class TaskType {

    @Desc(name = "ID")
    private Long id;

    @Desc(name="任务名称")
    private String name;

    @Desc(name="Cron表达式")
    private String cronString;

    @Desc(name="描述")
    private String description;

    @Desc(name="上次执行时间")
    private Date lastExecuteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCronString() {
        return cronString;
    }

    public void setCronString(String cronString) {
        this.cronString = cronString;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }
}
