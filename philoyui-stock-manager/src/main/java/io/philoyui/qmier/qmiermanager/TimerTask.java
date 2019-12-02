package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.util.Date;

@DescEntity(name = "timer_task", domainName = "定时器任务")
public class TimerTask {

    @Desc(name = "ID")
    private Long id;

    @Desc(name = "任务名称")
    private String name;

    @Desc(name = "任务ID")
    private Long taskTypeId;

    @Desc(name="启动时间",order = true)
    private Date startTime;

    @Desc(name = "结束时间",order = true)
    private Date endTime;

    @Desc(name= "是否成功")
    private Boolean success;

    @Desc(name="执行结果")
    private String executeResult;

    @Desc(name = "全部个数")
    private Long totalCount;

    @Desc(name = "完成个数")
    private Long completeTimes;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(String executeResult) {
        this.executeResult = executeResult;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getCompleteTimes() {
        return completeTimes;
    }

    public void setCompleteTimes(Long completeTimes) {
        this.completeTimes = completeTimes;
    }

    public Long getTaskTypeId() {
        return taskTypeId;
    }

    public void setTaskTypeId(Long taskTypeId) {
        this.taskTypeId = taskTypeId;
    }
}
