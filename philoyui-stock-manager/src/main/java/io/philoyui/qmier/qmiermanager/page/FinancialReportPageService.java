package io.philoyui.qmier.qmiermanager.page;

import cn.com.gome.cloud.openplatform.common.PageObject;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.button.batch.CreateOperation;
import cn.com.gome.page.button.column.DeleteOperation;
import cn.com.gome.page.button.column.EditOperation;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.DoubleFieldDefinition;
import cn.com.gome.page.field.LongFieldDefinition;
import cn.com.gome.page.field.StringFieldDefinition;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FinancialReportPageService extends PageService<FinancialReportEntity,Long> {

    @Autowired
    private FinancialReportService financialReportService;

    @Override
    public PageObject<FinancialReportEntity> paged(SearchFilter searchFilter) {
        return financialReportService.paged(searchFilter);
    }

    @Override
    protected PageConfig initializePageConfig(PageContext pageContext) {
        PageConfig pageConfig = new PageConfig(pageContext)
                .withDomainName("financial_report")
                .withDomainClass(FinancialReportEntity.class)
                .withDomainChineseName("财报信息")
                .withFieldDefinitions(
                        new LongFieldDefinition("id", "ID"),
                        new StringFieldDefinition("symbol", "代码"),
                        new DoubleFieldDefinition("roe", "资产收益率"),
                        new StringFieldDefinition("year", "年"),
                        new StringFieldDefinition("season", "季度"),
                        new DoubleFieldDefinition("earningsPerShare", "每股收益EPS"),
                        new DoubleFieldDefinition("debtRatio", "负债率"),
                        new StringFieldDefinition("reportName", "报告名称"),
                        new DoubleFieldDefinition("npPerShare", "每股净资产"),
                        new DoubleFieldDefinition("operateCashFlowPs", "每股经营现金流"),
                        new DoubleFieldDefinition("capitalReserve", "每股资本公积金"),
                        new DoubleFieldDefinition("undistriProfitPs", "应收账款周转天数"),
                        new DoubleFieldDefinition("netInterestOfTotalAssets", "总资产报酬率"),
                        new DoubleFieldDefinition("netSellingRate", "净利率"),
                        new DoubleFieldDefinition("grossSellingRate", "毛利率"),
                        new DoubleFieldDefinition("totalRevenue", "营业收入"),
                        new DoubleFieldDefinition("operatingIncomeYoy", "营业收入的增长率"),
                        new DoubleFieldDefinition("netProfitAtsopc", "净利润的增长率"),
                        new DoubleFieldDefinition("netProfit", "净利润"),
                        new DoubleFieldDefinition("netProfitAfterNrgalAtsolc", "扣非净利润"),
                        new DoubleFieldDefinition("npAtsopcNrgalYoy", "扣非净利润同比增长"),
                        new DoubleFieldDefinition("currentRatio", "流动比率"),
                        new DoubleFieldDefinition("quickRatio", "速动比率"),
                        new DoubleFieldDefinition("equityMultiplier", "权益乘数"),
                        new DoubleFieldDefinition("equityRatio", "权益比率"),
                        new DoubleFieldDefinition("holderEquity", "股东权益比率"),
                        new DoubleFieldDefinition("ncfFromOaToTotalLiab", "现金流量比率"),
                        new DoubleFieldDefinition("inventoryTurnoverDays", "存货周转天数"),
                        new DoubleFieldDefinition("receivableTurnoverDays", "应收账款周转天数"),
                        new DoubleFieldDefinition("accountsPayableTurnoverDays", "应付账款周转天数"),
                        new DoubleFieldDefinition("cashCycle", "现金循环周期"),
                        new DoubleFieldDefinition("operatingCycle", "营业周期"),
                        new DoubleFieldDefinition("totalCapitalTurnover", "总资产周转率"),
                        new DoubleFieldDefinition("inventoryTurnover", "存货周转率"),
                        new DoubleFieldDefinition("accountReceivableTurnover", "应收账款周转率"),
                        new DoubleFieldDefinition("accountsPayableTurnover", "应付账款周转率"),
                        new DoubleFieldDefinition("currentAssetTurnoverRate", "流动资产周转率")
                )
                .withTableColumnDefinitions(
                        "id_10",
                        "symbol_10",
                        "roe_10",
                        "year_10",
                        "season_10",
                        "earningsPerShare_10",
                        "debtRatio_10",
                        "reportName_10",
                        "npPerShare_10",
                        "operateCashFlowPs_10",
                        "capitalReserve_10",
                        "undistriProfitPs_10",
                        "netInterestOfTotalAssets_10",
                        "netSellingRate_10",
                        "grossSellingRate_10",
                        "totalRevenue_10",
                        "operatingIncomeYoy_10",
                        "netProfitAtsopc_10",
                        "netProfit_10",
                        "netProfitAfterNrgalAtsolc_10",
                        "npAtsopcNrgalYoy_10",
                        "currentRatio_10",
                        "quickRatio_10",
                        "equityMultiplier_10",
                        "equityRatio_10",
                        "holderEquity_10",
                        "ncfFromOaToTotalLiab_10",
                        "inventoryTurnoverDays_10",
                        "receivableTurnoverDays_10",
                        "accountsPayableTurnoverDays_10",
                        "cashCycle_10",
                        "operatingCycle_10",
                        "totalCapitalTurnover_10",
                        "inventoryTurnover_10",
                        "accountReceivableTurnover_10",
                        "accountsPayableTurnover_10",
                        "currentAssetTurnoverRate_10",
                        "#operation_10"
                )
                .withFilterDefinitions(
                )
                .withSortDefinitions(
                )
                .withTableAction(
                        new CreateOperation()
                )
                .withColumnAction(
                        new EditOperation(),
                        new DeleteOperation()
                )
                .withFormItemDefinition(
                        "id_rw",
                        "symbol_rw",
                        "roe_rw",
                        "year_rw",
                        "season_rw",
                        "earningsPerShare_rw",
                        "debtRatio_rw",
                        "reportName_rw",
                        "npPerShare_rw",
                        "operateCashFlowPs_rw",
                        "capitalReserve_rw",
                        "undistriProfitPs_rw",
                        "netInterestOfTotalAssets_rw",
                        "netSellingRate_rw",
                        "grossSellingRate_rw",
                        "totalRevenue_rw",
                        "operatingIncomeYoy_rw",
                        "netProfitAtsopc_rw",
                        "netProfit_rw",
                        "netProfitAfterNrgalAtsolc_rw",
                        "npAtsopcNrgalYoy_rw",
                        "currentRatio_rw",
                        "quickRatio_rw",
                        "equityMultiplier_rw",
                        "equityRatio_rw",
                        "holderEquity_rw",
                        "ncfFromOaToTotalLiab_rw",
                        "inventoryTurnoverDays_rw",
                        "receivableTurnoverDays_rw",
                        "accountsPayableTurnoverDays_rw",
                        "cashCycle_rw",
                        "operatingCycle_rw",
                        "totalCapitalTurnover_rw",
                        "inventoryTurnover_rw",
                        "accountReceivableTurnover_rw",
                        "accountsPayableTurnover_rw",
                        "currentAssetTurnoverRate_rw"
                );
        return pageConfig;
    }

    @Override
    public FinancialReportEntity get(String id) {
        return financialReportService.get(Long.parseLong(id));
    }

    @Override
    public FinancialReportEntity get(SearchFilter searchFilter) {
        return financialReportService.get(searchFilter);
    }

    @Override
    public void saveOrUpdate(FinancialReportEntity financialReport) {
        financialReportService.insert(financialReport);
    }

    @Override
    public void delete(FinancialReportEntity financialReport) {
        financialReportService.delete(financialReport.getId());
    }
}

