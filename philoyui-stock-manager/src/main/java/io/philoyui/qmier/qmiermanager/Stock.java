package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;
import com.google.gson.annotations.SerializedName;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 股票数据
 */
@DescEntity(name="股票数据",domainName="stock")
public class Stock implements Serializable {

    @Desc(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 股票名称
     */
    @Desc(name = "股票名称",filter = true)
    private String name;

    /**
     * 股票代码
     */
    @Desc(name = "股票代码",filter = true,order = true)
    @SerializedName("symbol")
    private String code;

    /**
     * 涨跌幅
     */
    @Desc(name = "涨跌幅",filter = true,order = true)
    private Double percent;

    /**
     * 流通股
     */
    @Desc(name = "流通股",order = true)
    @SerializedName("float_shares")
    private Long floatShares;

    /**
     * 当前价
     */
    @Desc(name = "当前价")
    @SerializedName("current")
    private Double currentPrice;

    /**
     * 振幅
     */
    @Desc(name="振幅")
    private Double amplitude;

    /**
     * 市值
     */
    @Desc(name = "市值",order = true)
    @SerializedName("market_capital")
    private Long marketCapital;

    /**
     * 股息率
     */
    @Desc(name = "股息率")
    @SerializedName("dividend_yield")
    private Double dividendYield;

    /**
     * 成交额
     */
    @Desc(name = "成交额",order = true)
    private Double amount;

    /**
     * 涨跌额
     */
    @Desc(name = "涨跌额")
    private Double chg;

    /**
     * 成交量
     */
    @Desc(name = "成交量",order = true)
    private Long volume;

    /**
     * 量比
     */
    @Desc(name = "量比",order = true)
    @SerializedName("volume_ratio")
    private Double volumeRatio;

    /**
     * 市净率
     */
    @Desc(name="市净率")
    private Double pb;

    /**
     * 换手率
     */
    @Desc(name = "换手率",order=true)
    @SerializedName("turnover_rate")
    private Double turnoverRate;

    /**
     * 市盈率
     */
    @Desc(name = "市盈率",order = true)
    @SerializedName("pe_ttm")
    private Double peTtm;

    /**
     * 总股本
     */
    @Desc(name = "总股本")
    @SerializedName("total_shares")
    private Long totalShares;

    public Stock() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getVolumeRatio() {
        return volumeRatio;
    }

    public void setVolumeRatio(Double volumeRatio) {
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

    public Long getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(Long totalShares) {
        this.totalShares = totalShares;
    }
}
