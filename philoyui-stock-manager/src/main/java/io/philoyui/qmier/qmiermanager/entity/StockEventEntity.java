package io.philoyui.qmier.qmiermanager.entity;

import io.philoyui.qmier.qmiermanager.entity.enu.EventType;

import java.io.Serializable;
import java.util.Date;

/**
 * 股票事件
 */
public class StockEventEntity implements Serializable {

    /**
     * ID
     */
    private Long id;

    /**
     * 股票代码
     */
    private String symbol;

    /**
     * 当天收盘价
     */
    private Double closeValue;

    /**
     * 日期
     */
    private String dayString;

    /**
     * 记录时间
     */
    private Date recordTime;

    /**
     * 事件类型
     */
    private EventType eventType;

    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public Double getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(Double closeValue) {
        this.closeValue = closeValue;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
