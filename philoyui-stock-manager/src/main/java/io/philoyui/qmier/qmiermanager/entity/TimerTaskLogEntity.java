package io.philoyui.qmier.qmiermanager.entity;

import io.philoyui.qmier.qmiermanager.entity.enu.TaskType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TimerTaskLogEntity implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务ID
     */
    private TaskType taskType;

    /**
     * 启动时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 持续多少分钟
     */
    private Long periodMinute;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 执行结果
     */
    private String executeResult;

    /**
     * 完成个数
     */
    private Long completeCount;


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

    public Long getCompleteCount() {
        return completeCount;
    }

    public void setCompleteCount(Long completeCount) {
        this.completeCount = completeCount;
    }

    public Long getPeriodMinute() {
        return periodMinute;
    }

    public void setPeriodMinute(Long periodMinute) {
        this.periodMinute = periodMinute;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType taskType) {
        this.taskType = taskType;
    }
}
