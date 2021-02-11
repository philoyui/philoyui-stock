package io.philoyui.bigdeal.entity;

import com.google.gson.annotations.SerializedName;
import io.philoyui.stock.client.east.data.BigBuyData;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class BigDealEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 录入时间
     */
    private String createdTime;

    /**
     * 股票代码
     */
    private String symbol;

    /**
     * 股票名称
     */
    private String stockName;

    /**
     * 涨跌幅
     */
    private double stockRange;

    /**
     * 收盘价
     */
    private double closePrice;

    /**
     * 成交价
     */
    private double dealPrice;

    /**
     * 折溢率
     */
    private double premiumDiscount;

    /**
     * 成交笔数
     */
    private int dealCount;

    /**
     * 成交总额
     */
    private double dealAmount;

    /**
     * 成交总量
     */
    private double dealVolume;

    /**
     * 成交总额/流通市值
     */
    private double cjeltszb;

    /**
     * 一天后涨幅
     */
    private String increase1;

    /**
     * 五天后涨幅
     */
    private String increase5;

    /**
     * 十天后涨幅
     */
    private String increase10;

    /**
     * 二十天后涨幅
     */
    private String increase20;

    /**
     * 买家
     */
    private String buyerName;

    private String buyerCode;

    /**
     * 卖家
     */
    private String salesName;

    private String salesCode;

    public static BigDealEntity constructFrom(BigBuyData bigBuyData) {
        BigDealEntity bigDealEntity = new BigDealEntity();
        bigDealEntity.setCreatedTime(bigBuyData.getCreatedTime());
        bigDealEntity.setSymbol(bigBuyData.getSymbol());
        bigDealEntity.setStockName(bigBuyData.getStockName());
        bigDealEntity.setStockRange(bigBuyData.getRange());
        bigDealEntity.setClosePrice(bigBuyData.getClosePrice());
        bigDealEntity.setDealPrice(bigBuyData.getDealPrice());
        bigDealEntity.setPremiumDiscount(bigBuyData.getPremiumDiscount());
        bigDealEntity.setDealCount(bigBuyData.getDealCount());
        bigDealEntity.setDealAmount(bigBuyData.getDealCount());
        bigDealEntity.setDealVolume(bigBuyData.getDealAmount());
        bigDealEntity.setCjeltszb(bigBuyData.getCjeltszb());
        bigDealEntity.setIncrease1(bigBuyData.getIncrease1());
        bigDealEntity.setIncrease5(bigBuyData.getIncrease5());
        bigDealEntity.setIncrease10(bigBuyData.getIncrease10());
        bigDealEntity.setIncrease20(bigBuyData.getIncrease20());
        bigDealEntity.setBuyerCode(bigBuyData.getBuyerCode());
        bigDealEntity.setBuyerName(bigBuyData.getBuyerName());
        bigDealEntity.setSalesName(bigBuyData.getSalesName());
        bigDealEntity.setSalesCode(bigBuyData.getSalesCode());
        return bigDealEntity;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getStockRange() {
        return stockRange;
    }

    public void setStockRange(double stockRange) {
        this.stockRange = stockRange;
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

    public double getCjeltszb() {
        return cjeltszb;
    }

    public void setCjeltszb(double cjeltszb) {
        this.cjeltszb = cjeltszb;
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

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerCode() {
        return buyerCode;
    }

    public void setBuyerCode(String buyerCode) {
        this.buyerCode = buyerCode;
    }

    public String getSalesName() {
        return salesName;
    }

    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }

    public String getSalesCode() {
        return salesCode;
    }

    public void setSalesCode(String salesCode) {
        this.salesCode = salesCode;
    }
}
