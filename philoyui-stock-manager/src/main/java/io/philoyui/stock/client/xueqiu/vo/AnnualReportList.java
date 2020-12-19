package io.philoyui.stock.client.xueqiu.vo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnnualReportList implements Serializable {

    /**
     * 股票名称
     */
    private String name;

    /**
     * 股票代码
     */
    @SerializedName("symbol")
    private String code;

    /**
     * 涨跌幅
     */
    private Double percent;

    /**
     * 流通股
     */
    @SerializedName("float_shares")
    private int floatShares;

    /**
     * 当前价
     */
    @SerializedName("current")
    private Double currentPrice;

    /**
     * 振幅
     */
    private Double amplitude;

    /**
     * 市值
     */
    @SerializedName("market_capital")
    private long marketCapital;

    /**
     * 股息率
     */
    @SerializedName("dividend_yield")
    private Double dividendYield;

    /**
     * 成交额
     */
    private int amount;

    /**
     * 涨跌额
     */
    private Double chg;

    /**
     * 成交量
     */
    private long volume;

    /**
     * 量比
     */
    @SerializedName("volume_ratio")
    private String volumeRatio;

    /**
     * 市净率
     */
    private Double pb;

    /**
     * 换手率
     */
    @SerializedName("turnover_rate")
    private Double turnoverRate;

    /**
     * 市盈率
     */
    @SerializedName("pe_ttm")
    private Double peTtm;

    /**
     * 总股本
     */
    @SerializedName("total_shares")
    private int totalShares;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public int getFloatShares() {
        return floatShares;
    }

    public void setFloatShares(int floatShares) {
        this.floatShares = floatShares;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }

    public long getMarketCapital() {
        return marketCapital;
    }

    public void setMarketCapital(long marketCapital) {
        this.marketCapital = marketCapital;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public String getVolumeRatio() {
        return volumeRatio;
    }

    public void setVolumeRatio(String volumeRatio) {
        this.volumeRatio = volumeRatio;
    }

    public Double getPb() {
        return pb;
    }

    public void setPb(Double pb) {
        this.pb = pb;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Double getPeTtm() {
        return peTtm;
    }

    public void setPeTtm(Double peTtm) {
        this.peTtm = peTtm;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }
}
