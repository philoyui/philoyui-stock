package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.io.Serializable;

@DescEntity(name = "trading_view", domainName = "TradingView")
public class TradingView implements Serializable {

    @Desc(name = "Id")
    private Long id;

    @Desc(name = "编码")
    private String symbol;

    @Desc(name = "股票名称")
    private String stockName;

    @Desc(name = "收盘价")
    private Double close;

    @Desc(name = "涨跌幅百分比")
    private Double change;

    @Desc(name = "涨跌幅百分比")
    private Double changeAbs;

    @Desc(name = "")
    private Double recommendScore;

    @Desc(name = "成交量")
    private Long volume;

    @Desc(name = "市值")
    private Long marketCap;

    @Desc(name = "市盈率")
    private Double priceEarningsTtm;

    @Desc(name = "雇员数")
    private Long numberOfEmployees;

    @Desc(name = "板块")
    private String sector;

    @Desc(name = "行业")
    private String industry;

    @Desc(name = "速动比率")
    private Double quickRatio;

    @Desc(name = "股东数")
    private Long numberOfShareholders;

    @Desc(name = "三只乌鸦")
    private Boolean blackCrows3;

    @Desc(name = "上吊线")
    private Boolean hangingMan;

    @Desc(name = "倒锤子线")
    private Boolean invertedHammer;

    @Desc(name = "光头阳线")
    private Boolean marubozuWhite;

    @Desc(name = "光头阴线")
    private Boolean marubozuBlack;

    @Desc(name = "十字星")
    private Boolean doji;

    @Desc(name = "启明星")
    private Boolean morningStar;

    @Desc(name = "墓碑线")
    private Boolean gravestone;

    @Desc(name = "流星线")
    private Boolean shootingStar;

    @Desc(name = "白三兵")
    private Boolean whiteSoldiers3;

    @Desc(name = "白色旋转陀螺")
    private Boolean spinningTopWhite;

    @Desc(name = "看涨三星")
    private Boolean triStarBullish;

    @Desc(name = "看涨反冲形态")
    private Boolean kickingBullish;

    @Desc(name = "看涨吞没")
    private Boolean engulfingBullish;

    @Desc(name = "看涨孕线")
    private Boolean haramiBullish;

    @Desc(name = "看涨弃婴")
    private Boolean abandonedBabyBullish;

    @Desc(name = "看跌三星")
    private Boolean triStarBearish;

    @Desc(name = "看跌反冲形态")
    private Boolean kickingBearish;

    @Desc(name = "看跌吞没")
    private Boolean engulfingBearish;

    @Desc(name = "看跌孕线")
    private Boolean haramiBearish;

    @Desc(name = "看跌弃婴")
    private Boolean abandonedBabyBearish;

    @Desc(name = "蜻蜓线")
    private Boolean dojiDragonfly;

    @Desc(name = "锤子线")
    private Boolean hammer;

    @Desc(name = "长上影线")
    private Boolean LongShadowUpper;

    @Desc(name = "黑丝旋转陀螺")
    private Boolean SpinningTopBlack;

    @Desc(name = "长下影线")
    private Boolean LongShadowLower;

    @Desc(name = "黄昏星")
    private Boolean eveningStar;

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

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
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

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
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

    public Long getNumberOfShareholders() {
        return numberOfShareholders;
    }

    public void setNumberOfShareholders(Long numberOfShareholders) {
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
        return LongShadowUpper;
    }

    public void setLongShadowUpper(Boolean longShadowUpper) {
        LongShadowUpper = longShadowUpper;
    }

    public Boolean getSpinningTopBlack() {
        return SpinningTopBlack;
    }

    public void setSpinningTopBlack(Boolean spinningTopBlack) {
        SpinningTopBlack = spinningTopBlack;
    }

    public Boolean getLongShadowLower() {
        return LongShadowLower;
    }

    public void setLongShadowLower(Boolean longShadowLower) {
        LongShadowLower = longShadowLower;
    }

    public Boolean getEveningStar() {
        return eveningStar;
    }

    public void setEveningStar(Boolean eveningStar) {
        this.eveningStar = eveningStar;
    }
}
