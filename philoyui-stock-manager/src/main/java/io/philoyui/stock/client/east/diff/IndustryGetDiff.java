package io.philoyui.stock.client.east.diff;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IndustryGetDiff implements Serializable {

    /**
     * 板块名称
     */
    @SerializedName("f14")
    private String name;

    /**
     * 最新价
     */
    @SerializedName("f2")
    private Double currentPrice;

    /**
     * 涨跌幅
     */
    @SerializedName("f3")
    private Double quoteRange;

    /**
     * 上涨家数
     */
    @SerializedName("f104")
    private Integer riseCount;


    /**
     * 下降家数
     */
    @SerializedName("f105")
    private Integer fallCount;

    /**
     * 领涨股票
     */
    @SerializedName("f")
    private String leaderName;

    /**
     * 代码
     */
    @SerializedName("f140")
    private String leaderCode;

    /**
     * 领涨涨幅
     */
    @SerializedName("f136")
    private Double leaderRange;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getLeaderCode() {
        return leaderCode;
    }

    public void setLeaderCode(String leaderCode) {
        this.leaderCode = leaderCode;
    }

    public Double getLeaderRange() {
        return leaderRange;
    }

    public void setLeaderRange(Double leaderRange) {
        this.leaderRange = leaderRange;
    }
}
