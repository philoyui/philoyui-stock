package io.philoyui.qmier.qmiermanager.entity;

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
     * 股票名称
     */
    private String stockName;

    /**
     * 加入时间
     */
    private Date addTime;

    /**
     * 分析时间
     */
    private Date analysisTime;

    /**
     * 分析结果
     */
    private String analysisResult;

    private Integer score;

    private String sarReason;

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

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getAnalysisTime() {
        return analysisTime;
    }

    public void setAnalysisTime(Date analysisTime) {
        this.analysisTime = analysisTime;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }

    public void setAnalysisResult(String analysisResult) {
        this.analysisResult = analysisResult;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSarReason() {
        return sarReason;
    }

    public void setSarReason(String sarReason) {
        this.sarReason = sarReason;
    }

}
