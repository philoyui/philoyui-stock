package io.philoyui.stock.client.east.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class BigBuyData implements Serializable {

    /**
     * 录入时间
     */
    @SerializedName("TDATE")
    private String createdTime;

    /**
     * 股票代码
     */
    @SerializedName("SECUCODE")
    private String symbol;

    /**
     * 股票名称
     */
    @SerializedName("SNAME")
    private String stockName;

    /**
     * 涨跌幅
     */
    @SerializedName("RCHANGE")
    private double range;

    /**
     * 收盘价
     */
    @SerializedName("CPRICE")
    private double closePrice;

    /**
     * 成交价
     */
    @SerializedName("PRICE")
    private double dealPrice;

    /**
     * 折溢率
     */
    @SerializedName("Zyl")
    private double premiumDiscount;

    /**
     * 成交笔数
     */
    @SerializedName("CJCount")
    private int dealCount;

    /**
     * 成交总额
     */
    @SerializedName("CJZE")
    private double dealAmount;

    /**
     * 成交总量
     */
    @SerializedName("CJZL")
    private double dealVolume;

    /**
     * 成交总额/流通市值
     */
    @SerializedName("Cjeltszb")
    private double Cjeltszb;

    /**
     * 一天后涨幅
     */
    @SerializedName("RCHANGE1DC")
    private String increase1;

    /**
     * 五天后涨幅
     */
    @SerializedName("RCHANGE5DC")
    private String increase5;

    /**
     * 十天后涨幅
     */
    @SerializedName("RCHANGE10DC")
    private String increase10;

    /**
     * 二十天后涨幅
     */
    @SerializedName("RCHANGE20DC")
    private String increase20;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }

    public double getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(double dealPrice) {
        this.dealPrice = dealPrice;
    }

    public double getPremiumDiscount() {
        return premiumDiscount;
    }

    public void setPremiumDiscount(double premiumDiscount) {
        this.premiumDiscount = premiumDiscount;
    }

    public int getDealCount() {
        return dealCount;
    }

    public void setDealCount(int dealCount) {
        this.dealCount = dealCount;
    }

    public double getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(double dealAmount) {
        this.dealAmount = dealAmount;
    }

    public double getDealVolume() {
        return dealVolume;
    }

    public void setDealVolume(double dealVolume) {
        this.dealVolume = dealVolume;
    }

    public void setDealAmount(int dealAmount) {
        this.dealAmount = dealAmount;
    }

    public void setDealVolume(int dealVolume) {
        this.dealVolume = dealVolume;
    }

    public double getCjeltszb() {
        return Cjeltszb;
    }

    public void setCjeltszb(double cjeltszb) {
        Cjeltszb = cjeltszb;
    }

    public String getIncrease1() {
        return increase1;
    }

    public void setIncrease1(String increase1) {
        this.increase1 = increase1;
    }

    public String getIncrease5() {
        return increase5;
    }

    public void setIncrease5(String increase5) {
        this.increase5 = increase5;
    }

    public String getIncrease10() {
        return increase10;
    }

    public void setIncrease10(String increase10) {
        this.increase10 = increase10;
    }

    public String getIncrease20() {
        return increase20;
    }

    public void setIncrease20(String increase20) {
        this.increase20 = increase20;
    }
}
