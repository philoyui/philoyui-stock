package io.philoyui.qmier.qmiermanager.entity.indicator;

import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;
import io.philoyui.qmier.qmiermanager.entity.indicator.enu.MacdType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class MacdDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private MacdType macdType;

    private String symbol;

    private Double macdValue;

    /**
     * 慢线
     */
    private Double signalValue;

    private Double histValue;

    private Double closeValue;

    private Date day;

    private String dayString;

    @Enumerated(value = EnumType.STRING)
    private IntervalType intervalType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MacdType getMacdType() {
        return macdType;
    }

    public void setMacdType(MacdType macdType) {
        this.macdType = macdType;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getMacdValue() {
        return macdValue;
    }

    public void setMacdValue(Double macdValue) {
        this.macdValue = macdValue;
    }

    public Double getSignalValue() {
        return signalValue;
    }

    public void setSignalValue(Double signalValue) {
        this.signalValue = signalValue;
    }

    public Double getHistValue() {
        return histValue;
    }

    public void setHistValue(Double histValue) {
        this.histValue = histValue;
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
}
