package io.philoyui.tagstock.entity;

import io.philoyui.stock.entity.enu.IntervalType;
import io.philoyui.stock.entity.indicator.enu.WrType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class WrDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private WrType wrType;

    private Date day;

    private String dayString;

    private double wr20Value;

    private String symbol;

    private double closeValue;

    @Enumerated(value = EnumType.STRING)
    private IntervalType intervalType;

    private Integer lastIndex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WrType getWrType() {
        return wrType;
    }

    public void setWrType(WrType wrType) {
        this.wrType = wrType;
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

    public double getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(double closeValue) {
        this.closeValue = closeValue;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(IntervalType intervalType) {
        this.intervalType = intervalType;
    }

    public double getWr20Value() {
        return wr20Value;
    }

    public void setWr20Value(double wr20Value) {
        this.wr20Value = wr20Value;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }
}
