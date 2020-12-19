package io.philoyui.tagstock.entity;

import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.entity.indicator.enu.BollType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class BollDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private BollType bollType;

    private String symbol;

    /**
     * 中轨
     */
    private Double bollMiddleValue;

    /**
     * 上轨
     */
    private Double bollUpperValue;

    /**
     * 下轨
     */
    private Double bollLowerValue;

    private Double closeValue;

    private Date day;

    private String dayString;

    @Enumerated(value = EnumType.STRING)
    private IntervalType intervalType;

    private Integer lastIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BollType getBollType() {
        return bollType;
    }

    public void setBollType(BollType bollType) {
        this.bollType = bollType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getBollMiddleValue() {
        return bollMiddleValue;
    }

    public void setBollMiddleValue(Double bollMiddleValue) {
        this.bollMiddleValue = bollMiddleValue;
    }

    public Double getBollUpperValue() {
        return bollUpperValue;
    }

    public void setBollUpperValue(Double bollUpperValue) {
        this.bollUpperValue = bollUpperValue;
    }

    public Double getBollLowerValue() {
        return bollLowerValue;
    }

    public void setBollLowerValue(Double bollLowerValue) {
        this.bollLowerValue = bollLowerValue;
    }

    public Double getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(Double closeValue) {
        this.closeValue = closeValue;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(IntervalType intervalType) {
        this.intervalType = intervalType;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }
}
