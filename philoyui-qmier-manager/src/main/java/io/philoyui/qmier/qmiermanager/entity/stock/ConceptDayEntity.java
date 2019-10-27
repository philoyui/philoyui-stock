package io.philoyui.qmier.qmiermanager.entity.stock;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 概念股的每日行情数据
 */
@Entity
public class ConceptDayEntity implements Serializable {

    @Id
    private Long id;

    /**
     * 概念名称
     */
    private String name;

    /**
     * 日期
     */
    private String dayString;

    /**
     * 记录日期
     */
    private Date recordTime;


    /**
     * 最新价
     */
    private Double currentPrice;

    /**
     * 涨跌幅
     */

    private Double quoteRange;

    /**
     * 上涨家数
     */
    private Integer riseCount;


    /**
     * 下降家数
     */
    private Integer fallCount;

    /**
     * 领涨股票
     */
    private String leaderName;

    /**
     * 代码
     */
    private String code;

    /**
     * 领涨涨幅
     */
    private Double leaderRange;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getQuoteRange() {
        return quoteRange;
    }

    public void setQuoteRange(Double quoteRange) {
        this.quoteRange = quoteRange;
    }

    public Integer getRiseCount() {
        return riseCount;
    }

    public void setRiseCount(Integer riseCount) {
        this.riseCount = riseCount;
    }

    public Integer getFallCount() {
        return fallCount;
    }

    public void setFallCount(Integer fallCount) {
        this.fallCount = fallCount;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getLeaderRange() {
        return leaderRange;
    }

    public void setLeaderRange(Double leaderRange) {
        this.leaderRange = leaderRange;
    }
}
