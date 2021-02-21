package io.philoyui.mystock.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * 自选股
 */
@Entity
public class MyStockEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 代码
     */
    private String symbol;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 时间标识，如2020-01-28
     */
    private String dateString;

    /**
     * 财报信息
     */
    private String financialReport;

    /**
     * 技术指标信息
     */
    private String technicalIndex;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 原因
     */
    private String reason;

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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getFinancialReport() {
        return financialReport;
    }

    public void setFinancialReport(String financialReport) {
        this.financialReport = financialReport;
    }

    public String getTechnicalIndex() {
        return technicalIndex;
    }

    public void setTechnicalIndex(String technicalIndex) {
        this.technicalIndex = technicalIndex;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
