package io.philoyui.stock.client.xueqiu;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class XueqiuList implements Serializable {

    @SerializedName("report_date")
    private Long reportDate;

    /**
     * 报告名称
     */
    @SerializedName("report_name")
    private String reportName;

    /**
     * ROE
     */
    @SerializedName("avg_roe")
    private List<Double> avgRoe;

    /**
     * 每股净资产
     */
    @SerializedName("np_per_share")
    private List<Double> npPerShare;

    /**
     * 每股经营现金流
     */
    @SerializedName("operate_cash_flow_ps")
    private List<Double> operateCashFlowPs;

    /**
     * 每股收益
     */
    @SerializedName("basic_eps")
    private List<Double> basicEps;

    /**
     * 每股资本公积金
     */
    @SerializedName("capital_reserve")
    private List<Double> capitalReserve;

    /**
     * 应收账款周转天数
     */
    @SerializedName("undistri_profit_ps")
    private List<Double> undistriProfitPs;

    /**
     * 总资产报酬率
     */
    @SerializedName("net_interest_of_total_assets")
    private List<Double> netInterestOfTotalAssets;

    /**
     * 销售净利率
     */
    @SerializedName("net_selling_rate")
    private List<Double> netSellingRate;

    /**
     * 销售毛利率
     */
    @SerializedName("gross_selling_rate")
    private List<Double> grossSellingRate;

    /**
     * 营业收入
     */
    @SerializedName("total_revenue")
    private List<Double> totalRevenue;

    /**
     * 营业收入同比增长
     */
    @SerializedName("operating_income_yoy")
    private List<Double> operatingIncomeYoy;

    /**
     * 营业收入同比增长
     */
    @SerializedName("net_profit_atsopc")
    private List<Double> netProfitAtsopc;

    /**
     * 净利润
     */
    @SerializedName("net_profit_atsopc_yoy")
    private List<Double> netProfitAtsopcYoy;

    /**
     * 扣非净利润
     */
    @SerializedName("net_profit_after_nrgal_atsolc")
    private List<Double> netProfitAfterNrgalAtsolc;

    /**
     * 扣非净利润同比增长
     */
    @SerializedName("np_atsopc_nrgal_yoy")
    private List<Double> npAtsopcNrgalYoy;

    /**
     * 净资产收益率-摊薄
     */
    @SerializedName("ore_dlt")
    private List<Double> oreDlt;

    /**
     * 资产负债率
     */
    @SerializedName("asset_liab_ratio")
    private List<Double> assetLiabRatio;

    /**
     * 流动比率
     */
    @SerializedName("current_ratio")
    private List<Double> currentRatio;
    /**
     * 速动比率
     */
    @SerializedName("quick_ratio")
    private List<Double> quickRatio;

    /**
     * 权益乘数
     */
    @SerializedName("equity_multiplier")
    private List<Double> equityMultiplier;

    /**
     * 产权比率
     */
    @SerializedName("equity_ratio")
    private List<Double> equityRatio;

    /**
     * 股东权益比率
     */
    @SerializedName("holder_equity")
    private List<Double> holderEquity;

    /**
     * 现金流量比率
     */
    @SerializedName("ncf_from_oa_to_total_liab")
    private List<Double> ncfFromOaToTotalLiab;

    /**
     * 存货周转天数
     */
    @SerializedName("inventory_turnover_days")
    private List<Double> inventoryTurnoverDays;

    /**
     *应收账款周转天数
     */
    @SerializedName("receivable_turnover_days")
    private List<Double> receivableTurnoverDays;

    /**
     * 应付账款周转天数
     */
    @SerializedName("accounts_payable_turnover_days")
    private List<Double> accountsPayableTurnoverDays;

    /**
     * 现金循环周期
     */
    @SerializedName("cash_cycle")
    private List<Double> cashCycle;

    /**
     * 营业周期
     */
    @SerializedName("operating_cycle")
    private List<Double> operatingCycle;

    /**
     * 总资产周转率
     */
    @SerializedName("total_capital_turnover")
    private List<Double> totalCapitalTurnover;

    /**
     * 存货周转率
     */
    @SerializedName("inventory_turnover")
    private List<Double> inventoryTurnover;

    /**
     * 应收账款周转率
     */
    @SerializedName("account_receivable_turnover")
    private List<Double> accountReceivableTurnover;

    /**
     * 应付账款周转率
     */
    @SerializedName("accounts_payable_turnover")
    private List<Double> accountsPayableTurnover;

    /**
     * 流动资产周转率
     */
    @SerializedName("current_asset_turnover_rate")
    private List<Double> currentAssetTurnoverRate;

    /**
     * 固定资产周转率
     */
    @SerializedName("fixed_asset_turnover_ratio")
    private List<Double> fixedAssetTurnoverRatio;


    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public List<Double> getAvgRoe() {
        return avgRoe;
    }

    public void setAvgRoe(List<Double> avgRoe) {
        this.avgRoe = avgRoe;
    }

    public List<Double> getNpPerShare() {
        return npPerShare;
    }

    public void setNpPerShare(List<Double> npPerShare) {
        this.npPerShare = npPerShare;
    }

    public List<Double> getOperateCashFlowPs() {
        return operateCashFlowPs;
    }

    public void setOperateCashFlowPs(List<Double> operateCashFlowPs) {
        this.operateCashFlowPs = operateCashFlowPs;
    }

    public List<Double> getBasicEps() {
        return basicEps;
    }

    public void setBasicEps(List<Double> basicEps) {
        this.basicEps = basicEps;
    }

    public List<Double> getCapitalReserve() {
        return capitalReserve;
    }

    public void setCapitalReserve(List<Double> capitalReserve) {
        this.capitalReserve = capitalReserve;
    }

    public List<Double> getUndistriProfitPs() {
        return undistriProfitPs;
    }

    public void setUndistriProfitPs(List<Double> undistriProfitPs) {
        this.undistriProfitPs = undistriProfitPs;
    }

    public List<Double> getNetInterestOfTotalAssets() {
        return netInterestOfTotalAssets;
    }

    public void setNetInterestOfTotalAssets(List<Double> netInterestOfTotalAssets) {
        this.netInterestOfTotalAssets = netInterestOfTotalAssets;
    }

    public List<Double> getNetSellingRate() {
        return netSellingRate;
    }

    public void setNetSellingRate(List<Double> netSellingRate) {
        this.netSellingRate = netSellingRate;
    }

    public List<Double> getGrossSellingRate() {
        return grossSellingRate;
    }

    public void setGrossSellingRate(List<Double> grossSellingRate) {
        this.grossSellingRate = grossSellingRate;
    }

    public List<Double> getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(List<Double> totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<Double> getOperatingIncomeYoy() {
        return operatingIncomeYoy;
    }

    public void setOperatingIncomeYoy(List<Double> operatingIncomeYoy) {
        this.operatingIncomeYoy = operatingIncomeYoy;
    }

    public List<Double> getNetProfitAtsopc() {
        return netProfitAtsopc;
    }

    public void setNetProfitAtsopc(List<Double> netProfitAtsopc) {
        this.netProfitAtsopc = netProfitAtsopc;
    }

    public List<Double> getNetProfitAtsopcYoy() {
        return netProfitAtsopcYoy;
    }

    public void setNetProfitAtsopcYoy(List<Double> netProfitAtsopcYoy) {
        this.netProfitAtsopcYoy = netProfitAtsopcYoy;
    }

    public List<Double> getNetProfitAfterNrgalAtsolc() {
        return netProfitAfterNrgalAtsolc;
    }

    public void setNetProfitAfterNrgalAtsolc(List<Double> netProfitAfterNrgalAtsolc) {
        this.netProfitAfterNrgalAtsolc = netProfitAfterNrgalAtsolc;
    }

    public List<Double> getNpAtsopcNrgalYoy() {
        return npAtsopcNrgalYoy;
    }

    public void setNpAtsopcNrgalYoy(List<Double> npAtsopcNrgalYoy) {
        this.npAtsopcNrgalYoy = npAtsopcNrgalYoy;
    }

    public List<Double> getOreDlt() {
        return oreDlt;
    }

    public void setOreDlt(List<Double> oreDlt) {
        this.oreDlt = oreDlt;
    }

    public List<Double> getAssetLiabRatio() {
        return assetLiabRatio;
    }

    public void setAssetLiabRatio(List<Double> assetLiabRatio) {
        this.assetLiabRatio = assetLiabRatio;
    }

    public List<Double> getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(List<Double> currentRatio) {
        this.currentRatio = currentRatio;
    }

    public List<Double> getQuickRatio() {
        return quickRatio;
    }

    public void setQuickRatio(List<Double> quickRatio) {
        this.quickRatio = quickRatio;
    }

    public List<Double> getEquityMultiplier() {
        return equityMultiplier;
    }

    public void setEquityMultiplier(List<Double> equityMultiplier) {
        this.equityMultiplier = equityMultiplier;
    }

    public List<Double> getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(List<Double> equityRatio) {
        this.equityRatio = equityRatio;
    }

    public List<Double> getHolderEquity() {
        return holderEquity;
    }

    public void setHolderEquity(List<Double> holderEquity) {
        this.holderEquity = holderEquity;
    }

    public List<Double> getNcfFromOaToTotalLiab() {
        return ncfFromOaToTotalLiab;
    }

    public void setNcfFromOaToTotalLiab(List<Double> ncfFromOaToTotalLiab) {
        this.ncfFromOaToTotalLiab = ncfFromOaToTotalLiab;
    }

    public List<Double> getInventoryTurnoverDays() {
        return inventoryTurnoverDays;
    }

    public void setInventoryTurnoverDays(List<Double> inventoryTurnoverDays) {
        this.inventoryTurnoverDays = inventoryTurnoverDays;
    }

    public List<Double> getReceivableTurnoverDays() {
        return receivableTurnoverDays;
    }

    public void setReceivableTurnoverDays(List<Double> receivableTurnoverDays) {
        this.receivableTurnoverDays = receivableTurnoverDays;
    }

    public List<Double> getAccountsPayableTurnoverDays() {
        return accountsPayableTurnoverDays;
    }

    public void setAccountsPayableTurnoverDays(List<Double> accountsPayableTurnoverDays) {
        this.accountsPayableTurnoverDays = accountsPayableTurnoverDays;
    }

    public List<Double> getCashCycle() {
        return cashCycle;
    }

    public void setCashCycle(List<Double> cashCycle) {
        this.cashCycle = cashCycle;
    }

    public List<Double> getOperatingCycle() {
        return operatingCycle;
    }

    public void setOperatingCycle(List<Double> operatingCycle) {
        this.operatingCycle = operatingCycle;
    }

    public List<Double> getTotalCapitalTurnover() {
        return totalCapitalTurnover;
    }

    public void setTotalCapitalTurnover(List<Double> totalCapitalTurnover) {
        this.totalCapitalTurnover = totalCapitalTurnover;
    }

    public List<Double> getInventoryTurnover() {
        return inventoryTurnover;
    }

    public void setInventoryTurnover(List<Double> inventoryTurnover) {
        this.inventoryTurnover = inventoryTurnover;
    }

    public List<Double> getAccountReceivableTurnover() {
        return accountReceivableTurnover;
    }

    public void setAccountReceivableTurnover(List<Double> accountReceivableTurnover) {
        this.accountReceivableTurnover = accountReceivableTurnover;
    }

    public List<Double> getAccountsPayableTurnover() {
        return accountsPayableTurnover;
    }

    public void setAccountsPayableTurnover(List<Double> accountsPayableTurnover) {
        this.accountsPayableTurnover = accountsPayableTurnover;
    }

    public List<Double> getCurrentAssetTurnoverRate() {
        return currentAssetTurnoverRate;
    }

    public void setCurrentAssetTurnoverRate(List<Double> currentAssetTurnoverRate) {
        this.currentAssetTurnoverRate = currentAssetTurnoverRate;
    }

    public List<Double> getFixedAssetTurnoverRatio() {
        return fixedAssetTurnoverRatio;
    }

    public void setFixedAssetTurnoverRatio(List<Double> fixedAssetTurnoverRatio) {
        this.fixedAssetTurnoverRatio = fixedAssetTurnoverRatio;
    }

    public Long getReportDate() {
        return reportDate;
    }

    public void setReportDate(Long reportDate) {
        this.reportDate = reportDate;
    }
}
