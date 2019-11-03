package io.philoyui.qmier.qmiermanager.entity;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class StockEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private Long floatShares;

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
    private Long marketCapital;

    /**
     * 股息率
     */
    @SerializedName("dividend_yield")
    private Double dividendYield;

    /**
     * 成交额
     */
    private Double amount;

    /**
     * 涨跌额
     */
    private Double chg;

    /**
     * 成交量
     */
    private Long volume;

    /**
     * 量比
     */
    @SerializedName("volume_ratio")
    private Double volumeRatio;

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
    private Long totalShares;

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

    public Long getFloatShares() {
        return floatShares;
    }

    public void setFloatShares(Long floatShares) {
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

    public Long getMarketCapital() {
        return marketCapital;
    }

    public void setMarketCapital(Long marketCapital) {
        this.marketCapital = marketCapital;
    }

    public Double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(Double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getChg() {
        return chg;
    }

    public void setChg(Double chg) {
        this.chg = chg;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
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

    public Long getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(Long totalShares) {
        this.totalShares = totalShares;
    }

    public Double getVolumeRatio() {
        return volumeRatio;
    }

    public void setVolumeRatio(Double volumeRatio) {
        this.volumeRatio = volumeRatio;
    }
}
