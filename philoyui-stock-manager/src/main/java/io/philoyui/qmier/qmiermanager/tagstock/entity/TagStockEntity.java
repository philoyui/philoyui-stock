package io.philoyui.qmier.qmiermanager.tagstock.entity;

import io.philoyui.qmier.qmiermanager.domain.StockAndReason;
import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 股票标签
 */
@Entity
@Table(name="stock_tag")
public class TagStockEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 编号
     */
    private String symbol;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 创建时间
     */
    private Date createdTime;

    private String dayString;

    private String lastDayString;

    /**
     * 时间区间
     */
    private IntervalType intervalType;

    /**
     * 倒数第几天
     */
    private Integer lastIndex;


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

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public StockAndReason buildStockAndReason(){
        return new StockAndReason(symbol,dayString,tagName);
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

    public String getLastDayString() {
        return lastDayString;
    }

    public void setLastDayString(String lastDayString) {
        this.lastDayString = lastDayString;
    }
}
