package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

@DescEntity(name = "财报信息", domainName = "financial_report")
public class FinancialReport {

    @Desc(name = "ID")
    private Long id;

    @Desc(name = "代码")
    private String symbol;


    /**
     * 大于15%较为优质
     */
    @Desc(name="资产收益率")
    private Double roe;

    /**
     * 年
     */
    @Desc(name = "年")
    private String year;

    /**
     * 季度
     */
    @Desc(name = "季度")
    private String season;

    /**
     * 每股收益 f55
     */
    @Desc(name = "每股收益EPS")
    private Double earningsPerShare;

    /**
     * 负债率，大于70%属于风险比较大的
     */
    @Desc(name = "负债率")
    private Double debtRatio;

    /**
     * 报告名称
     */
    @Desc(name = "报告名称")
    private String reportName;

    /**
     * 每股净资产
     */
    @Desc(name="每股净资产")
    private Double npPerShare;

    /**
     * 每股经营现金流，抗风险强，主营业务收入回款能力强，产品竞争能力强，获利能力强，不是越多越好，
     */
    @Desc(name="每股经营现金流")
    private Double operateCashFlowPs;

    /**
     * 每股资本公积金，该值越大，高送转可能比较大
     */
    @Desc(name="每股资本公积金")
    private Double capitalReserve;

    /**
     * 应收账款周转天数，大于150说明周转过于慢，资金使用用效率大幅提高，偿债能力强，注意季节性企业不准
     */
    @Desc(name = "应收账款周转天数")
    private Double undistriProfitPs;

    /**
     * 总资产报酬率
     */
    @Desc(name = "总资产报酬率")
    private Double netInterestOfTotalAssets;

    /**
     * 销售净利率
     */
    @Desc(name="净利率")
    private Double netSellingRate;

    /**
     * 销售毛利率，产品的销售能力，越高说明卖的越好
     */
    @Desc(name = "毛利率")
    private Double grossSellingRate;

    /**
     * 营业收入
     */
    @Desc(name="营业收入")
    private Double totalRevenue;

    /**
     * 营业收入的增长率
     */
    @Desc(name="营业收入的增长率")
    private Double operatingIncomeYoy;

    /**
     * 净利润的增长率
     */
    @Desc(name="净利润的增长率")
    private Double netProfitAtsopc;

    /**
     * 净利润
     */
    @Desc(name = "净利润")
    private Double netProfit;

    /**
     * 扣非净利润，相对比净利润更能体现赚钱能力
     */
    @Desc(name="扣非净利润")
    private Double netProfitAfterNrgalAtsolc;

    /**
     * 扣非净利润同比增长
     */
    @Desc(name="扣非净利润同比增长")
    private Double npAtsopcNrgalYoy;

    /**
     * 流动比率，产品卖得快不快，偿债能力怎么样，欠别人的钱能不能还上，连续5年大于200%
     */
    @Desc(name="流动比率")
    private Double currentRatio;

    /**
     * 速动比率，偿债能力，大于100%则偿债能力比较好
     */
    @Desc(name = "速动比率")
    private Double quickRatio;

    /**
     * 权益乘数，权益乘数越大，资产负债比率就越大，企业财务风险就越大，偿还长期债务的能力就越弱。
     */
    @Desc(name="权益乘数")
    private Double equityMultiplier;

    /**
     * 权益比率
     */
    @Desc(name="权益比率")
    private Double equityRatio;

    /**
     * 股东权益比率
     */
    @Desc(name="股东权益比率")
    private Double holderEquity;

    /**
     * 现金流量比率，最好大于100%
     */
    @Desc(name="现金流量比率")
    private Double ncfFromOaToTotalLiab;

    /**
     * 存货周转天数
     */
    @Desc(name="存货周转天数")
    private Double inventoryTurnoverDays;

    /**
     *应收账款周转天数
     */
    @Desc(name="应收账款周转天数")
    private Double receivableTurnoverDays;

    /**
     * 应付账款周转天数
     */
    @Desc(name="应付账款周转天数")
    private Double accountsPayableTurnoverDays;

    /**
     * 现金循环周期
     */
    @Desc(name="现金循环周期")
    private Double cashCycle;

    /**
     * 营业周期
     */
    @Desc(name="营业周期")
    private Double operatingCycle;

    /**
     * 总资产周转率
     */
    @Desc(name="总资产周转率")
    private Double totalCapitalTurnover;

    /**
     * 存货周转率
     */
    @Desc(name="存货周转率")
    private Double inventoryTurnover;

    /**
     * 应收账款周转率
     */
    @Desc(name="应收账款周转率")
    private Double accountReceivableTurnover;

    /**
     * 应付账款周转率
     */
    @Desc(name="应付账款周转率")
    private Double accountsPayableTurnover;

    /**
     * 流动资产周转率
     */
    @Desc(name="流动资产周转率")
    private Double currentAssetTurnoverRate;

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

    public Double getRoe() {
        return roe;
    }

    public void setRoe(Double roe) {
        this.roe = roe;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
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

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
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
}
