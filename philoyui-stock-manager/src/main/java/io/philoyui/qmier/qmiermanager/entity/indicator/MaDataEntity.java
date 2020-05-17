package io.philoyui.qmier.qmiermanager.entity.indicator;

import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.enu.MaType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MaDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MaType maType;

    private Date day;

    private String dayString;

    private double ma5Value;

    private double ma10Value;

    private double ma20Value;

    private double ma30Value;

    private String symbol;

    private double closeValue;

    @Enumerated(value = EnumType.STRING)
    private IntervalType intervalType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaType getMaType() {
        return maType;
    }

    public void setMaType(MaType maType) {
        this.maType = maType;
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

    public double getMa5Value() {
        return ma5Value;
    }

    public void setMa5Value(double ma5Value) {
        this.ma5Value = ma5Value;
    }

    public double getMa10Value() {
        return ma10Value;
    }

    public void setMa10Value(double ma10Value) {
        this.ma10Value = ma10Value;
    }

    public double getMa30Value() {
        return ma30Value;
    }

    public void setMa30Value(double ma30Value) {
        this.ma30Value = ma30Value;
    }

    public double getMa20Value() {
        return ma20Value;
    }

    public void setMa20Value(double ma20Value) {
        this.ma20Value = ma20Value;
    }
}
