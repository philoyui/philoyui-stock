package io.philoyui.tradingview.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TradingViewEntity implements Serializable {

    /**
     * Id
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 编码
     */
    private String symbol;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 收盘价
     */
    private Double close;

    /**
     * 涨跌幅百分比
     */
    private Double todayChange;

    /**
     * 涨跌幅百分比
     */
    private Double changeAbs;

    /**
     * 
     */
    private Double recommendScore;

    /**
     * 成交量
     */
    private Long volume;

    /**
     * 市值
     */
    private Double marketCap;

    /**
     * 市盈率
     */
    private Double priceEarningsTtm;

    /**
     * 雇员数
     */
    private Long numberOfEmployees;

    /**
     * 板块
     */
    private String sector;

    /**
     * 行业
     */
    private String industry;

    /**
     * 速动比率
     */
    private Double quickRatio;

    /**
     * 股东数
     */
    private Double numberOfShareholders;

    /**
     * 三只乌鸦
     */
    private Boolean blackCrows3;

    /**
     * 上吊线
     */
    private Boolean hangingMan;

    /**
     * 倒锤子线
     */
    private Boolean invertedHammer;

    /**
     * 光头阳线
     */
    private Boolean marubozuWhite;

    /**
     * 光头阴线
     */
    private Boolean marubozuBlack;

    /**
     * 十字星
     */
    private Boolean doji;

    /**
     * 启明星
     */
    private Boolean morningStar;

    /**
     * 墓碑线
     */
    private Boolean gravestone;

    /**
     * 流星线
     */

    private Boolean shootingStar;


    /**
     * 白三兵
     */
    private Boolean whiteSoldiers3;

    /**
     * 白色旋转陀螺
     */
    private Boolean spinningTopWhite;

    /**
     * 看涨三星
     */
    private Boolean triStarBullish;

    /**
     * 看涨反冲形态
     */
    private Boolean kickingBullish;

    /**
     * 看涨吞没
     */
    private Boolean engulfingBullish;

    /**
     * 看涨孕线
     */
    private Boolean haramiBullish;

    /**
     * 看涨弃婴
     */
    private Boolean abandonedBabyBullish;

    /**
     * 看跌三星
     */
    private Boolean triStarBearish;

    /**
     * 看跌反冲形态
     */
    private Boolean kickingBearish;

    /**
     * 看跌吞没
     */
    private Boolean engulfingBearish;

    /**
     * 看跌孕线
     */
    private Boolean haramiBearish;

    /**
     * 看跌弃婴
     */
    private Boolean abandonedBabyBearish;

    /**
     * 蜻蜓线
     */
    private Boolean dojiDragonfly;

    /**
     * 锤子线
     */
    private Boolean hammer;

    /**
     * 长上影线
     */
    private Boolean longShadowUpper;

    /**
     * 黑丝旋转陀螺
     */
    private Boolean spinningTopBlack;

    /**
     * 长下影线
     */
    private Boolean longShadowLower;

    /**
     * 黄昏星
     */
    private Boolean eveningStar;

    /**
     * 流通股
     */
    private Double outstandingShares;

    /**
     * 换手率
     */
    private Double turnOver;

    /**
     * 指数移动平均线 多头
     */
    private Boolean ema20;

    /**
     * 移动平均线 多头
     */
    private Boolean sma30;

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

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getTodayChange() {
        return todayChange;
    }

    public void setTodayChange(Double todayChange) {
        this.todayChange = todayChange;
    }

    public Double getChangeAbs() {
        return changeAbs;
    }

    public void setChangeAbs(Double changeAbs) {
        this.changeAbs = changeAbs;
    }

    public Double getRecommendScore() {
        return recommendScore;
    }

    public void setRecommendScore(Double recommendScore) {
        this.recommendScore = recommendScore;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Double getPriceEarningsTtm() {
        return priceEarningsTtm;
    }

    public void setPriceEarningsTtm(Double priceEarningsTtm) {
        this.priceEarningsTtm = priceEarningsTtm;
    }

    public Long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void setNumberOfEmployees(Long numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public Double getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(Double quickRatio) {
        this.quickRatio = quickRatio;
    }

    public Double getNumberOfShareholders() {
        return numberOfShareholders;
    }

    public void setNumberOfShareholders(Double numberOfShareholders) {
        this.numberOfShareholders = numberOfShareholders;
    }

    public Boolean getBlackCrows3() {
        return blackCrows3;
    }

    public void setBlackCrows3(Boolean blackCrows3) {
        this.blackCrows3 = blackCrows3;
    }

    public Boolean getHangingMan() {
        return hangingMan;
    }

    public void setHangingMan(Boolean hangingMan) {
        this.hangingMan = hangingMan;
    }

    public Boolean getInvertedHammer() {
        return invertedHammer;
    }

    public void setInvertedHammer(Boolean invertedHammer) {
        this.invertedHammer = invertedHammer;
    }

    public Boolean getMarubozuWhite() {
        return marubozuWhite;
    }

    public void setMarubozuWhite(Boolean marubozuWhite) {
        this.marubozuWhite = marubozuWhite;
    }

    public Boolean getMarubozuBlack() {
        return marubozuBlack;
    }

    public void setMarubozuBlack(Boolean marubozuBlack) {
        this.marubozuBlack = marubozuBlack;
    }

    public Boolean getDoji() {
        return doji;
    }

    public void setDoji(Boolean doji) {
        this.doji = doji;
    }

    public Boolean getMorningStar() {
        return morningStar;
    }

    public void setMorningStar(Boolean morningStar) {
        this.morningStar = morningStar;
    }

    public Boolean getGravestone() {
        return gravestone;
    }

    public void setGravestone(Boolean gravestone) {
        this.gravestone = gravestone;
    }

    public Boolean getShootingStar() {
        return shootingStar;
    }

    public void setShootingStar(Boolean shootingStar) {
        this.shootingStar = shootingStar;
    }

    public Boolean getWhiteSoldiers3() {
        return whiteSoldiers3;
    }

    public void setWhiteSoldiers3(Boolean whiteSoldiers3) {
        this.whiteSoldiers3 = whiteSoldiers3;
    }

    public Boolean getSpinningTopWhite() {
        return spinningTopWhite;
    }

    public void setSpinningTopWhite(Boolean spinningTopWhite) {
        this.spinningTopWhite = spinningTopWhite;
    }

    public Boolean getTriStarBullish() {
        return triStarBullish;
    }

    public void setTriStarBullish(Boolean triStarBullish) {
        this.triStarBullish = triStarBullish;
    }

    public Boolean getKickingBullish() {
        return kickingBullish;
    }

    public void setKickingBullish(Boolean kickingBullish) {
        this.kickingBullish = kickingBullish;
    }

    public Boolean getEngulfingBullish() {
        return engulfingBullish;
    }

    public void setEngulfingBullish(Boolean engulfingBullish) {
        this.engulfingBullish = engulfingBullish;
    }

    public Boolean getHaramiBullish() {
        return haramiBullish;
    }

    public void setHaramiBullish(Boolean haramiBullish) {
        this.haramiBullish = haramiBullish;
    }

    public Boolean getAbandonedBabyBullish() {
        return abandonedBabyBullish;
    }

    public void setAbandonedBabyBullish(Boolean abandonedBabyBullish) {
        this.abandonedBabyBullish = abandonedBabyBullish;
    }

    public Boolean getTriStarBearish() {
        return triStarBearish;
    }

    public void setTriStarBearish(Boolean triStarBearish) {
        this.triStarBearish = triStarBearish;
    }

    public Boolean getKickingBearish() {
        return kickingBearish;
    }

    public void setKickingBearish(Boolean kickingBearish) {
        this.kickingBearish = kickingBearish;
    }

    public Boolean getEngulfingBearish() {
        return engulfingBearish;
    }

    public void setEngulfingBearish(Boolean engulfingBearish) {
        this.engulfingBearish = engulfingBearish;
    }

    public Boolean getHaramiBearish() {
        return haramiBearish;
    }

    public void setHaramiBearish(Boolean haramiBearish) {
        this.haramiBearish = haramiBearish;
    }

    public Boolean getAbandonedBabyBearish() {
        return abandonedBabyBearish;
    }

    public void setAbandonedBabyBearish(Boolean abandonedBabyBearish) {
        this.abandonedBabyBearish = abandonedBabyBearish;
    }

    public Boolean getDojiDragonfly() {
        return dojiDragonfly;
    }

    public void setDojiDragonfly(Boolean dojiDragonfly) {
        this.dojiDragonfly = dojiDragonfly;
    }

    public Boolean getHammer() {
        return hammer;
    }

    public void setHammer(Boolean hammer) {
        this.hammer = hammer;
    }

    public Boolean getLongShadowUpper() {
        return longShadowUpper;
    }

    public void setLongShadowUpper(Boolean LongShadowUpper) {
        this.longShadowUpper = LongShadowUpper;
    }

    public Boolean getSpinningTopBlack() {
        return spinningTopBlack;
    }

    public void setSpinningTopBlack(Boolean SpinningTopBlack) {
        this.spinningTopBlack = SpinningTopBlack;
    }

    public Boolean getLongShadowLower() {
        return longShadowLower;
    }

    public void setLongShadowLower(Boolean LongShadowLower) {
        this.longShadowLower = LongShadowLower;
    }

    public Boolean getEveningStar() {
        return eveningStar;
    }

    public void setEveningStar(Boolean eveningStar) {
        this.eveningStar = eveningStar;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public Double getOutstandingShares() {
        return outstandingShares;
    }

    public void setOutstandingShares(Double outstandingShares) {
        this.outstandingShares = outstandingShares;
    }

    public Double getTurnOver() {
        return turnOver;
    }

    public void setTurnOver(Double turnOver) {
        this.turnOver = turnOver;
    }

    public Boolean getEma20() {
        return ema20;
    }

    public void setEma20(Boolean ema20) {
        this.ema20 = ema20;
    }

    public Boolean getSma30() {
        return sma30;
    }

    public void setSma30(Boolean sma30) {
        this.sma30 = sma30;
    }
}
