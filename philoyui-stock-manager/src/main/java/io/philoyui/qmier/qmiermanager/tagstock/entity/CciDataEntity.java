package io.philoyui.qmier.qmiermanager.tagstock.entity;

import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.CciType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class CciDataEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Date day;

    private String dayString;

    private String symbol;

    private double cciValue;

    private double closeValue;

    @Enumerated(value = EnumType.STRING)
    private IntervalType intervalType;

    @Enumerated(value=EnumType.STRING)
    private CciType cciType;

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

    public double getCciValue() {
        return cciValue;
    }

    public void setCciValue(double cciValue) {
        this.cciValue = cciValue;
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

    public CciType getCciType() {
        return cciType;
    }

    public void setCciType(CciType cciType) {
        this.cciType = cciType;
    }

    public Integer getLastIndex() {
        return lastIndex;
    }

    public void setLastIndex(Integer lastIndex) {
        this.lastIndex = lastIndex;
    }
}
