package io.philoyui.qmier.qmiermanager.entity.stock;

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
     * 股票名称 f14
     */
    @SerializedName("f14")
    private String name;

    /**
     * 股票代码 f12
     */
    @SerializedName("f12")
    private String code;


    /**
     * 最新价 f2
     */
    @SerializedName("f2")
    private Double currentPrice;

    /**
     * 涨跌幅 f3
     */
    @SerializedName("f3")
    private Double changeRange;

    /**
     * 涨跌额 f4
     */
    @SerializedName("f4")
    private Double changePrice;

    /**
     * 成交量 f5
     */
    @SerializedName("f5")
    private long volume;

    /**
     * 成交额 f6
     */
    @SerializedName("f6")
    private long turnover;

    /**
     * 总市值 f20
     */
    @SerializedName("f20")
    private long marketCapitalization;

    /**
     * 振幅 f7
     */
    @SerializedName("f7")
    private Double amplitude;

    /**
     * 最高价 f15
     */
    @SerializedName("f15")
    private Double highPrice;

    /**
     * 最低价 f16
     */
    @SerializedName("f16")
    private Double lowPrice;

    /**
     * 开盘价 f17
     */
    @SerializedName("f17")
    private Double startPrice;

    /**
     * 昨天收盘价 f18
     */
    @SerializedName("f18")
    private Double yesterdayPrice;

    /**
     * 量比 f10
     */
    @SerializedName("f10")
    private Double quantityRelativeRatio;

    /**
     * 换手率 f8
     */
    @SerializedName("f8")
    private Double turnoverRate;

    /**
     * 市盈率 f9
     */
    @SerializedName("f9")
    private Double priceEarningsRatio;

    /**
     * 市净率 f23
     */
    @SerializedName("f23")
    private Double priceToBookRatio;

    /**
     * 主力净流入额 f62
     */
    @SerializedName("f62")
    private long mainNetInflowOfFunds;

    /**
     * 今日主力净流入占比 f184
     */
    @SerializedName("f184")
    private Double mainNetInflowOfFundsRatio;

    /**
     * 超大单净流入额 f66
     */
    @SerializedName("f66")
    private long oversizeInflowOfFunds;

    /**
     * 超大单净流入占比 f69
     */
    @SerializedName("f69")
    private Double oversizeInflowOfFundsRatio;

    /**
     * 大单净流入额 f72
     */
    @SerializedName("f72")
    private long bigSizeInflowOfFunds;

    /**
     * 大单净流入占比 f75
     */
    @SerializedName("f75")
    private Double bigSizeInflowOfFundsRatio;

    /**
     * 中单净流入额 f78
     */
    @SerializedName("f78")
    private long middleSizeInflowOfFunds;

    /**
     * 中单净流入占比 f81
     */
    @SerializedName("f81")
    private Double middleSizeInflowOfFundsRatio;

    /**
     * 小单净流入额 f84
     */
    @SerializedName("f84")
    private long smallSizeInflowOfFunds;

    /**
     * 小单净流入占比 f87
     */
    @SerializedName("f87")
    private Double smallSizeInflowOfFundsRatio;


    /**
     * 净资产收益率 f37
     */
    @SerializedName("f37")
    private Double roe;

    /**
     * 每股收益 f55
     */
    @SerializedName("f55")
    private Double earningsPerShare;

    /**
     * 负债率 f57
     */
    @SerializedName("f57")
    private Double debtRatio;

    /**
     * 毛利率 f49
     */
    @SerializedName("f49")
    private Double grossMargin;

    /**
     * 主营业务收入 f41
     */
    @SerializedName("f41")
    private Double salesRevenue;

    /**
     * 行业 f127
     */
    @SerializedName("f100")
    private String industry;

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

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getChangeRange() {
        return changeRange;
    }

    public void setChangeRange(Double changeRange) {
        this.changeRange = changeRange;
    }

    public Double getChangePrice() {
        return changePrice;
    }

    public void setChangePrice(Double changePrice) {
        this.changePrice = changePrice;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getTurnover() {
        return turnover;
    }

    public void setTurnover(long turnover) {
        this.turnover = turnover;
    }

    public long getMarketCapitalization() {
        return marketCapitalization;
    }

    public void setMarketCapitalization(long marketCapitalization) {
        this.marketCapitalization = marketCapitalization;
    }

    public Double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(Double amplitude) {
        this.amplitude = amplitude;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public Double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getYesterdayPrice() {
        return yesterdayPrice;
    }

    public void setYesterdayPrice(Double yesterdayPrice) {
        this.yesterdayPrice = yesterdayPrice;
    }

    public Double getQuantityRelativeRatio() {
        return quantityRelativeRatio;
    }

    public void setQuantityRelativeRatio(Double quantityRelativeRatio) {
        this.quantityRelativeRatio = quantityRelativeRatio;
    }

    public Double getTurnoverRate() {
        return turnoverRate;
    }

    public void setTurnoverRate(Double turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Double getPriceEarningsRatio() {
        return priceEarningsRatio;
    }

    public void setPriceEarningsRatio(Double priceEarningsRatio) {
        this.priceEarningsRatio = priceEarningsRatio;
    }

    public Double getPriceToBookRatio() {
        return priceToBookRatio;
    }

    public void setPriceToBookRatio(Double priceToBookRatio) {
        this.priceToBookRatio = priceToBookRatio;
    }

    public long getMainNetInflowOfFunds() {
        return mainNetInflowOfFunds;
    }

    public void setMainNetInflowOfFunds(long mainNetInflowOfFunds) {
        this.mainNetInflowOfFunds = mainNetInflowOfFunds;
    }

    public Double getMainNetInflowOfFundsRatio() {
        return mainNetInflowOfFundsRatio;
    }

    public void setMainNetInflowOfFundsRatio(Double mainNetInflowOfFundsRatio) {
        this.mainNetInflowOfFundsRatio = mainNetInflowOfFundsRatio;
    }

    public long getOversizeInflowOfFunds() {
        return oversizeInflowOfFunds;
    }

    public void setOversizeInflowOfFunds(long oversizeInflowOfFunds) {
        this.oversizeInflowOfFunds = oversizeInflowOfFunds;
    }

    public Double getOversizeInflowOfFundsRatio() {
        return oversizeInflowOfFundsRatio;
    }

    public void setOversizeInflowOfFundsRatio(Double oversizeInflowOfFundsRatio) {
        this.oversizeInflowOfFundsRatio = oversizeInflowOfFundsRatio;
    }

    public long getBigSizeInflowOfFunds() {
        return bigSizeInflowOfFunds;
    }

    public void setBigSizeInflowOfFunds(long bigSizeInflowOfFunds) {
        this.bigSizeInflowOfFunds = bigSizeInflowOfFunds;
    }

    public Double getBigSizeInflowOfFundsRatio() {
        return bigSizeInflowOfFundsRatio;
    }

    public void setBigSizeInflowOfFundsRatio(Double bigSizeInflowOfFundsRatio) {
        this.bigSizeInflowOfFundsRatio = bigSizeInflowOfFundsRatio;
    }

    public long getMiddleSizeInflowOfFunds() {
        return middleSizeInflowOfFunds;
    }

    public void setMiddleSizeInflowOfFunds(long middleSizeInflowOfFunds) {
        this.middleSizeInflowOfFunds = middleSizeInflowOfFunds;
    }

    public Double getMiddleSizeInflowOfFundsRatio() {
        return middleSizeInflowOfFundsRatio;
    }

    public void setMiddleSizeInflowOfFundsRatio(Double middleSizeInflowOfFundsRatio) {
        this.middleSizeInflowOfFundsRatio = middleSizeInflowOfFundsRatio;
    }

    public long getSmallSizeInflowOfFunds() {
        return smallSizeInflowOfFunds;
    }

    public void setSmallSizeInflowOfFunds(long smallSizeInflowOfFunds) {
        this.smallSizeInflowOfFunds = smallSizeInflowOfFunds;
    }

    public Double getSmallSizeInflowOfFundsRatio() {
        return smallSizeInflowOfFundsRatio;
    }

    public void setSmallSizeInflowOfFundsRatio(Double smallSizeInflowOfFundsRatio) {
        this.smallSizeInflowOfFundsRatio = smallSizeInflowOfFundsRatio;
    }

    public Double getRoe() {
        return roe;
    }

    public void setRoe(Double roe) {
        this.roe = roe;
    }

    public Double getEarningsPerShare() {
        return earningsPerShare;
    }

    public void setEarningsPerShare(Double earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }

    public Double getDebtRatio() {
        return debtRatio;
    }

    public void setDebtRatio(Double debtRatio) {
        this.debtRatio = debtRatio;
    }

    public Double getGrossMargin() {
        return grossMargin;
    }

    public void setGrossMargin(Double grossMargin) {
        this.grossMargin = grossMargin;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Double getSalesRevenue() {
        return salesRevenue;
    }

    public void setSalesRevenue(Double salesRevenue) {
        this.salesRevenue = salesRevenue;
    }
}
