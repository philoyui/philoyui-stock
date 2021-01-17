package io.philoyui.stockdetail.entity;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class StockDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 代码
     */
    private String symbol;

    /**
     * 现金充足
     */
    private String caToAssetInfo;

    /**
     * 现金占比值
     */
    private Double caToAssetValue;

    /**
     * 毛利率，生意是否好做
     */
    private String gpMarginInfo;

    /**
     * 毛利率值
     */
    private Double gpMarginValue;

    /**
     * 净利率，生意是否赚钱
     */
    private String npMarginInfo;

    /**
     * 净利率值
     */
    private Double npMarginValue;


    /**
     * 回报率，股东是否好赚钱
     */
    private String roeAvgInfo;

    /**
     * 回报率信息
     */
    private Double roeAvgValue;

    /**
     * 应收账款周转，收款速度
     */
    private String nrTurnDaysInfo;

    /**
     * 应收账款值
     */
    private Double nrTurnDaysValue;

    /**
     * 总资产周转率，经营效率
     */
    private String assetTurnRatioInfo;

    /**
     * 总资产周转率值
     */
    private Double assetTurnRatioValue;

    /**
     * 存货周转率，货卖的情况
     */
    private String invTurnDaysInfo;

    /**
     * 存货周转率值
     */
    private Double invTurnDaysValue;

    /**
     * 资产负债，杠杆情况
     */
    private String liabilityToAssetInfo;

    /**
     * 资产负债值
     */
    private Double liabilityToAssetValue;

    /**
     * 速动比率，清偿问题
     */
    private String quickRatioInfo;

    /**
     * 速动比率值
     */
    private Double quickRatioValue;

    /**
     * 市盈率
     */
    private String epsInfo;

    /**
     * 换手率
     */
    private String turnOverInfo;

    /**
     * 创建时间
     */
    private Date createdTime = new Date();

    /**
     * 修改时间
     */
    private Date modifyTime;

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

    public String getEpsInfo() {
        return epsInfo;
    }

    public void setEpsInfo(String epsInfo) {
        this.epsInfo = epsInfo;
    }

    public String getTurnOverInfo() {
        return turnOverInfo;
    }

    public void setTurnOverInfo(String turnOverInfo) {
        this.turnOverInfo = turnOverInfo;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getCaToAssetInfo() {
        return caToAssetInfo;
    }

    public void setCaToAssetInfo(String caToAssetInfo) {
        this.caToAssetInfo = caToAssetInfo;
    }

    public Double getCaToAssetValue() {
        return caToAssetValue;
    }

    public void setCaToAssetValue(Double caToAssetValue) {
        this.caToAssetValue = caToAssetValue;
    }

    public String getGpMarginInfo() {
        return gpMarginInfo;
    }

    public void setGpMarginInfo(String gpMarginInfo) {
        this.gpMarginInfo = gpMarginInfo;
    }

    public Double getGpMarginValue() {
        return gpMarginValue;
    }

    public void setGpMarginValue(Double gpMarginValue) {
        this.gpMarginValue = gpMarginValue;
    }

    public Double getNpMarginValue() {
        return npMarginValue;
    }

    public void setNpMarginValue(Double npMarginValue) {
        this.npMarginValue = npMarginValue;
    }

    public String getRoeAvgInfo() {
        return roeAvgInfo;
    }

    public void setRoeAvgInfo(String roeAvgInfo) {
        this.roeAvgInfo = roeAvgInfo;
    }

    public Double getRoeAvgValue() {
        return roeAvgValue;
    }

    public void setRoeAvgValue(Double roeAvgValue) {
        this.roeAvgValue = roeAvgValue;
    }

    public String getNrTurnDaysInfo() {
        return nrTurnDaysInfo;
    }

    public void setNrTurnDaysInfo(String nrTurnDaysInfo) {
        this.nrTurnDaysInfo = nrTurnDaysInfo;
    }

    public Double getNrTurnDaysValue() {
        return nrTurnDaysValue;
    }

    public void setNrTurnDaysValue(Double nrTurnDaysValue) {
        this.nrTurnDaysValue = nrTurnDaysValue;
    }

    public String getAssetTurnRatioInfo() {
        return assetTurnRatioInfo;
    }

    public void setAssetTurnRatioInfo(String assetTurnRatioInfo) {
        this.assetTurnRatioInfo = assetTurnRatioInfo;
    }

    public Double getAssetTurnRatioValue() {
        return assetTurnRatioValue;
    }

    public void setAssetTurnRatioValue(Double assetTurnRatioValue) {
        this.assetTurnRatioValue = assetTurnRatioValue;
    }

    public String getLiabilityToAssetInfo() {
        return liabilityToAssetInfo;
    }

    public void setLiabilityToAssetInfo(String liabilityToAssetInfo) {
        this.liabilityToAssetInfo = liabilityToAssetInfo;
    }

    public Double getLiabilityToAssetValue() {
        return liabilityToAssetValue;
    }

    public void setLiabilityToAssetValue(Double liabilityToAssetValue) {
        this.liabilityToAssetValue = liabilityToAssetValue;
    }

    public String getQuickRatioInfo() {
        return quickRatioInfo;
    }

    public void setQuickRatioInfo(String quickRatioInfo) {
        this.quickRatioInfo = quickRatioInfo;
    }

    public Double getQuickRatioValue() {
        return quickRatioValue;
    }

    public void setQuickRatioValue(Double quickRatioValue) {
        this.quickRatioValue = quickRatioValue;
    }

    public String getNpMarginInfo() {
        return npMarginInfo;
    }

    public void setNpMarginInfo(String npMarginInfo) {
        this.npMarginInfo = npMarginInfo;
    }

    public String getInvTurnDaysInfo() {
        return invTurnDaysInfo;
    }

    public void setInvTurnDaysInfo(String invTurnDaysInfo) {
        this.invTurnDaysInfo = invTurnDaysInfo;
    }

    public Double getInvTurnDaysValue() {
        return invTurnDaysValue;
    }

    public void setInvTurnDaysValue(Double invTurnDaysValue) {
        this.invTurnDaysValue = invTurnDaysValue;
    }

    public List<String> buildDescribeItems() {
        ArrayList<String> describeItems = Lists.newArrayList();
        if(!Strings.isNullOrEmpty(caToAssetInfo)){
            describeItems.add(caToAssetInfo);
        }
        if(!Strings.isNullOrEmpty(gpMarginInfo)){
            describeItems.add(gpMarginInfo);
        }
        if(!Strings.isNullOrEmpty(npMarginInfo)){
            describeItems.add(npMarginInfo);
        }
        if(!Strings.isNullOrEmpty(nrTurnDaysInfo)){
            describeItems.add(nrTurnDaysInfo);
        }
        if(!Strings.isNullOrEmpty(roeAvgInfo)){
            describeItems.add(roeAvgInfo);
        }
        if(!Strings.isNullOrEmpty(nrTurnDaysInfo)){
            describeItems.add(nrTurnDaysInfo);
        }
        if(!Strings.isNullOrEmpty(assetTurnRatioInfo)){
            describeItems.add(assetTurnRatioInfo);
        }
        if(!Strings.isNullOrEmpty(invTurnDaysInfo)){
            describeItems.add(invTurnDaysInfo);
        }
        if(!Strings.isNullOrEmpty(liabilityToAssetInfo)){
            describeItems.add(liabilityToAssetInfo);
        }

        if(!Strings.isNullOrEmpty(quickRatioInfo)){
            describeItems.add(quickRatioInfo);
        }

        if(!Strings.isNullOrEmpty(epsInfo)){
            describeItems.add(epsInfo);
        }

        if(!Strings.isNullOrEmpty(turnOverInfo)){
            describeItems.add(turnOverInfo);
        }
        return describeItems;
    }
}
