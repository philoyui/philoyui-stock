package io.philoyui.tagstock.entity;

import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.entity.indicator.enu.KdjType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class KdjDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date day;

    private String dayString;

    private String symbol;

    private double kValue;

    private double jValue;

    private double dValue;

    private double closeValue;

    @Enumerated(value = EnumType.STRING)
    private IntervalType intervalType;

    @Enumerated(value=EnumType.STRING)
    private KdjType kdjType;

    private Integer lastIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getkValue() {
        return kValue;
    }

    public void setkValue(double kValue) {
        this.kValue = kValue;
    }

    public double getjValue() {
        return jValue;
    }

    public void setjValue(double jValue) {
        this.jValue = jValue;
    }

    public double getdValue() {
        return dValue;
    }

    public void setdValue(double dValue) {
        this.dValue = dValue;
    }

    public double getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(double closeValue) {
        this.closeValue = closeValue;
    }

    public KdjType getKdjType() {
        return kdjType;
    }

    public void setKdjType(KdjType kdjType) {
        this.kdjType = kdjType;
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
