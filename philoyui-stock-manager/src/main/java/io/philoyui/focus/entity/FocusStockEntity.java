package io.philoyui.focus.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class FocusStockEntity implements Serializable {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 股票代码
     */
    private String symbol;

    /**
     * 加入时间
     */
    private Date addTime;

    /**
     * 原因
     */
    private String reason;

    /**
     * 相关标签
     */
    private String tags;

    /**
     * 等级
     */
    private Integer level;

    /**
     * 财务报表
     */
    private String financialReport;

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getFinancialReport() {
        return financialReport;
    }

    public void setFinancialReport(String financialReport) {
        this.financialReport = financialReport;
    }
}
