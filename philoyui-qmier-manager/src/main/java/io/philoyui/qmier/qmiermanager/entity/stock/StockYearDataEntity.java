package io.philoyui.qmier.qmiermanager.entity.stock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 股票的年线数据
 */
@Entity
public class StockYearDataEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 年
     */
    private String year;

    /**
     * 资产收益率
     */
    private Double roe;


    /**
     * 每股收益 f55
     */
    private Double earningsPerShare;

    /**
     * 负债率 f57
     */
    private Double debtRatio;

    /**
     * 报告名称
     */
    private String reportName;

    /**
     * 每股净资产
     */
    private Double npPerShare;

    /**
     * 每股经营现金流
     */
    private Double operateCashFlowPs;

    /**
     * 每股资本公积金
     */
    private Double capitalReserve;

    /**
     * 应收账款周转天数
     */
    private Double undistriProfitPs;

    /**
     * 总资产报酬率
     */
    private Double netInterestOfTotalAssets;

    /**
     * 销售净利率
     */
    private Double netSellingRate;

    /**
     * 销售毛利率
     */
    private Double grossSellingRate;

    /**
     * 营业收入
     */
    private Double totalRevenue;

    /**
     * 营业收入同比增长
     */
    private Double operatingIncomeYoy;

    /**
     * 营业收入同比增长
     */
    private Double netProfitAtsopc;

    /**
     * 净利润
     */
    private Double netProfitAtsopcYoy;

    /**
     * 扣非净利润
     */
    private Double netProfitAfterNrgalAtsolc;

    /**
     * 扣非净利润同比增长
     */
    private Double npAtsopcNrgalYoy;

    /**
     * 净资产收益率-摊薄
     */
    private Double oreDlt;

    /**
     * 流动比率
     */
    private Double currentRatio;
    /**
     * 速动比率
     */
    private Double quickRatio;

    /**
     * 权益乘数
     */
    private Double equityMultiplier;

    /**
     * 产权比率
     */
    private Double equityRatio;

    /**
     * 股东权益比率
     */
    private Double holderEquity;

    /**
     * 现金流量比率
     */
    private Double ncfFromOaToTotalLiab;

    /**
     * 存货周转天数
     */
    private Double inventoryTurnoverDays;

    /**
     *应收账款周转天数
     */
    private Double receivableTurnoverDays;

    /**
     * 应付账款周转天数
     */
    private Double accountsPayableTurnoverDays;

    /**
     * 现金循环周期
     */
    private Double cashCycle;

    /**
     * 营业周期
     */
    private Double operatingCycle;

    /**
     * 总资产周转率
     */
    private Double totalCapitalTurnover;

    /**
     * 存货周转率
     */
    private Double inventoryTurnover;

    /**
     * 应收账款周转率
     */
    private Double accountReceivableTurnover;

    /**
     * 应付账款周转率
     */
    private Double accountsPayableTurnover;

    /**
     * 流动资产周转率
     */
    private Double currentAssetTurnoverRate;

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

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Double getNpPerShare() {
        return npPerShare;
    }

    public void setNpPerShare(Double npPerShare) {
        this.npPerShare = npPerShare;
    }

    public Double getOperateCashFlowPs() {
        return operateCashFlowPs;
    }

    public void setOperateCashFlowPs(Double operateCashFlowPs) {
        this.operateCashFlowPs = operateCashFlowPs;
    }

    public Double getCapitalReserve() {
        return capitalReserve;
    }

    public void setCapitalReserve(Double capitalReserve) {
        this.capitalReserve = capitalReserve;
    }

    public Double getUndistriProfitPs() {
        return undistriProfitPs;
    }

    public void setUndistriProfitPs(Double undistriProfitPs) {
        this.undistriProfitPs = undistriProfitPs;
    }

    public Double getNetInterestOfTotalAssets() {
        return netInterestOfTotalAssets;
    }

    public void setNetInterestOfTotalAssets(Double netInterestOfTotalAssets) {
        this.netInterestOfTotalAssets = netInterestOfTotalAssets;
    }

    public Double getNetSellingRate() {
        return netSellingRate;
    }

    public void setNetSellingRate(Double netSellingRate) {
        this.netSellingRate = netSellingRate;
    }

    public Double getGrossSellingRate() {
        return grossSellingRate;
    }

    public void setGrossSellingRate(Double grossSellingRate) {
        this.grossSellingRate = grossSellingRate;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Double getOperatingIncomeYoy() {
        return operatingIncomeYoy;
    }

    public void setOperatingIncomeYoy(Double operatingIncomeYoy) {
        this.operatingIncomeYoy = operatingIncomeYoy;
    }

    public Double getNetProfitAtsopc() {
        return netProfitAtsopc;
    }

    public void setNetProfitAtsopc(Double netProfitAtsopc) {
        this.netProfitAtsopc = netProfitAtsopc;
    }

    public Double getNetProfitAtsopcYoy() {
        return netProfitAtsopcYoy;
    }

    public void setNetProfitAtsopcYoy(Double netProfitAtsopcYoy) {
        this.netProfitAtsopcYoy = netProfitAtsopcYoy;
    }

    public Double getNetProfitAfterNrgalAtsolc() {
        return netProfitAfterNrgalAtsolc;
    }

    public void setNetProfitAfterNrgalAtsolc(Double netProfitAfterNrgalAtsolc) {
        this.netProfitAfterNrgalAtsolc = netProfitAfterNrgalAtsolc;
    }

    public Double getNpAtsopcNrgalYoy() {
        return npAtsopcNrgalYoy;
    }

    public void setNpAtsopcNrgalYoy(Double npAtsopcNrgalYoy) {
        this.npAtsopcNrgalYoy = npAtsopcNrgalYoy;
    }

    public Double getOreDlt() {
        return oreDlt;
    }

    public void setOreDlt(Double oreDlt) {
        this.oreDlt = oreDlt;
    }

    public Double getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(Double currentRatio) {
        this.currentRatio = currentRatio;
    }

    public Double getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(Double quickRatio) {
        this.quickRatio = quickRatio;
    }

    public Double getEquityMultiplier() {
        return equityMultiplier;
    }

    public void setEquityMultiplier(Double equityMultiplier) {
        this.equityMultiplier = equityMultiplier;
    }

    public Double getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(Double equityRatio) {
        this.equityRatio = equityRatio;
    }

    public Double getHolderEquity() {
        return holderEquity;
    }

    public void setHolderEquity(Double holderEquity) {
        this.holderEquity = holderEquity;
    }

    public Double getNcfFromOaToTotalLiab() {
        return ncfFromOaToTotalLiab;
    }

    public void setNcfFromOaToTotalLiab(Double ncfFromOaToTotalLiab) {
        this.ncfFromOaToTotalLiab = ncfFromOaToTotalLiab;
    }

    public Double getInventoryTurnoverDays() {
        return inventoryTurnoverDays;
    }

    public void setInventoryTurnoverDays(Double inventoryTurnoverDays) {
        this.inventoryTurnoverDays = inventoryTurnoverDays;
    }

    public Double getReceivableTurnoverDays() {
        return receivableTurnoverDays;
    }

    public void setReceivableTurnoverDays(Double receivableTurnoverDays) {
        this.receivableTurnoverDays = receivableTurnoverDays;
    }

    public Double getAccountsPayableTurnoverDays() {
        return accountsPayableTurnoverDays;
    }

    public void setAccountsPayableTurnoverDays(Double accountsPayableTurnoverDays) {
        this.accountsPayableTurnoverDays = accountsPayableTurnoverDays;
    }

    public Double getCashCycle() {
        return cashCycle;
    }

    public void setCashCycle(Double cashCycle) {
        this.cashCycle = cashCycle;
    }

    public Double getOperatingCycle() {
        return operatingCycle;
    }

    public void setOperatingCycle(Double operatingCycle) {
        this.operatingCycle = operatingCycle;
    }

    public Double getTotalCapitalTurnover() {
        return totalCapitalTurnover;
    }

    public void setTotalCapitalTurnover(Double totalCapitalTurnover) {
        this.totalCapitalTurnover = totalCapitalTurnover;
    }

    public Double getInventoryTurnover() {
        return inventoryTurnover;
    }

    public void setInventoryTurnover(Double inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }

    public Double getAccountReceivableTurnover() {
        return accountReceivableTurnover;
    }

    public void setAccountReceivableTurnover(Double accountReceivableTurnover) {
        this.accountReceivableTurnover = accountReceivableTurnover;
    }

    public Double getAccountsPayableTurnover() {
        return accountsPayableTurnover;
    }

    public void setAccountsPayableTurnover(Double accountsPayableTurnover) {
        this.accountsPayableTurnover = accountsPayableTurnover;
    }

    public Double getCurrentAssetTurnoverRate() {
        return currentAssetTurnoverRate;
    }

    public void setCurrentAssetTurnoverRate(Double currentAssetTurnoverRate) {
        this.currentAssetTurnoverRate = currentAssetTurnoverRate;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
