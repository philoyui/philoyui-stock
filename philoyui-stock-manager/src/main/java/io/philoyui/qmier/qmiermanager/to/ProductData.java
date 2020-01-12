package io.philoyui.qmier.qmiermanager.to;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductData implements Serializable {

    private String symbol;
    private String code;
    private String name;
    private String trade;

    @SerializedName("pricechange")
    private String priceChange;

    @SerializedName("changepercent")
    private String changePercent;
    private String buy;
    private String sell;
    private String settlement;
    private String open;
    private String high;
    private String low;
    private Long volume;
    private Long amount;

    @SerializedName("ticktime")
    private String tickTime;
    private double per;

    /**
     * 市净率
     */
    private double pb;

    @SerializedName("mktcap")
    private double mktCap;

    /**
     * 市净率
     */
    private double nmc;

    /**
     * 换手率
     */
    @SerializedName("turnoverratio")
    private double turnOverRatio;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(String priceChange) {
        this.priceChange = priceChange;
    }

    public String getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTickTime() {
        return tickTime;
    }

    public void setTickTime(String tickTime) {
        this.tickTime = tickTime;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }

    public double getPb() {
        return pb;
    }

    public void setPb(double pb) {
        this.pb = pb;
    }

    public double getMktCap() {
        return mktCap;
    }

    public void setMktCap(double mktCap) {
        this.mktCap = mktCap;
    }

    public double getNmc() {
        return nmc;
    }

    public void setNmc(double nmc) {
        this.nmc = nmc;
    }

    public double getTurnOverRatio() {
        return turnOverRatio;
    }

    public void setTurnOverRatio(double turnOverRatio) {
        this.turnOverRatio = turnOverRatio;
    }
}
