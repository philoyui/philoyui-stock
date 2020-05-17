package io.philoyui.qmier.qmiermanager.entity;

import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name="stock_indicator")
public class StockIndicatorEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 是否启用
     */
    private boolean enable;

    /**
     * Python文件名
     */
    private String pythonName;

    /**
     * 唯一标识
     */
    private String identifier;

    /**
     * 上次执行事件
     */
    private Date lastExecuteTime;

    /**
     * 时间间隔
     */
    private IntervalType intervalType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPythonName() {
        return pythonName;
    }

    public void setPythonName(String pythonName) {
        this.pythonName = pythonName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(IntervalType intervalType) {
        this.intervalType = intervalType;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

}
