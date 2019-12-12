package io.philoyui.qmier.qmiermanager.service.impl;

import cn.com.gome.cloud.openplatform.repository.GenericDao;
import cn.com.gome.cloud.openplatform.service.impl.GenericServiceImpl;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClient;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClientImpl;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueqiuList;
import io.philoyui.qmier.qmiermanager.client.xueqiu.request.FinancialReportRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.FinancialReportResponse;
import io.philoyui.qmier.qmiermanager.dao.FinancialReportDao;
import io.philoyui.qmier.qmiermanager.entity.FinancialProductEntity;
import io.philoyui.qmier.qmiermanager.entity.FinancialReportEntity;
import io.philoyui.qmier.qmiermanager.service.FinancialReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FinancialReportServiceImpl extends GenericServiceImpl<FinancialReportEntity,Long> implements FinancialReportService {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialReportServiceImpl.class);

    @Autowired
    private FinancialReportDao financialReportDao;

    @Override
    protected GenericDao<FinancialReportEntity, Long> getDao() {
        return financialReportDao;
    }

    @Override
    public void insertAll(List<FinancialReportEntity> financialReports) {
        financialReportDao.saveAll(financialReports);
    }

    @Retryable(value = {Exception.class}, maxAttempts = 3, backoff = @Backoff(delay = 5000L, multiplier = 2))
    @Override
    public void downloadHistory(FinancialProductEntity stockEntity) {

        XueQiuClient client = new XueQiuClientImpl();
        FinancialReportRequest request = new FinancialReportRequest();
        request.setSymbol(stockEntity.getSymbol());
        FinancialReportResponse response = client.execute(request);

        List<FinancialReportEntity> financialReports = new ArrayList<>();
        for (XueqiuList xueqiuList : response.getData().getList()) {
            FinancialReportEntity financialReportEntity = new FinancialReportEntity();
            financialReportEntity.setSymbol(stockEntity.getSymbol());
            financialReportEntity.setRoe(xueqiuList.getAvgRoe().get(0));
            financialReportEntity.setYear(xueqiuList.getReportName().substring(0,4));
            financialReportEntity.setSeason(xueqiuList.getReportName().substring(4));
            financialReportEntity.setEarningsPerShare(xueqiuList.getNpPerShare().get(0));
            financialReportEntity.setDebtRatio(xueqiuList.getAssetLiabRatio().get(0));
            financialReportEntity.setReportName(xueqiuList.getReportName());
            financialReportEntity.setNpPerShare(xueqiuList.getNpPerShare().get(0));
            financialReportEntity.setOperateCashFlowPs(xueqiuList.getOperateCashFlowPs().get(0));
            financialReportEntity.setCapitalReserve(xueqiuList.getCapitalReserve().get(0));
            financialReportEntity.setUndistriProfitPs(xueqiuList.getUndistriProfitPs().get(0));
            financialReportEntity.setNetInterestOfTotalAssets(xueqiuList.getNetInterestOfTotalAssets().get(0));
            financialReportEntity.setNetSellingRate(xueqiuList.getNetSellingRate().get(0));
            financialReportEntity.setGrossSellingRate(xueqiuList.getGrossSellingRate().get(0));
            financialReportEntity.setTotalRevenue(xueqiuList.getTotalRevenue().get(0));
            financialReportEntity.setOperatingIncomeYoy(xueqiuList.getOperatingIncomeYoy().get(0));
            financialReportEntity.setNetProfitAtsopc(xueqiuList.getNetProfitAtsopc().get(0));
            financialReportEntity.setNetProfit(xueqiuList.getNetProfitAtsopcYoy().get(0));
            financialReportEntity.setNetProfitAfterNrgalAtsolc(xueqiuList.getNetProfitAfterNrgalAtsolc().get(0));
            financialReportEntity.setNpAtsopcNrgalYoy(xueqiuList.getNpAtsopcNrgalYoy().get(0));
            financialReportEntity.setCurrentRatio(xueqiuList.getCurrentRatio().get(0));
            financialReportEntity.setQuickRatio(xueqiuList.getQuickRatio().get(0));
            financialReportEntity.setEquityMultiplier(xueqiuList.getEquityMultiplier().get(0));
            financialReportEntity.setEquityRatio(xueqiuList.getEquityRatio().get(0));
            financialReportEntity.setHolderEquity(xueqiuList.getHolderEquity().get(0));
            financialReportEntity.setNcfFromOaToTotalLiab(xueqiuList.getNcfFromOaToTotalLiab().get(0));
            financialReportEntity.setInventoryTurnoverDays(xueqiuList.getInventoryTurnoverDays().get(0));
            financialReportEntity.setReceivableTurnoverDays(xueqiuList.getReceivableTurnoverDays().get(0));
            financialReportEntity.setAccountsPayableTurnoverDays(xueqiuList.getAccountsPayableTurnoverDays().get(0));
            financialReportEntity.setCashCycle(xueqiuList.getCashCycle().get(0));
            financialReportEntity.setOperatingCycle(xueqiuList.getOperatingCycle().get(0));
            financialReportEntity.setTotalCapitalTurnover(xueqiuList.getTotalCapitalTurnover().get(0));
            financialReportEntity.setInventoryTurnover(xueqiuList.getInventoryTurnover().get(0));
            financialReportEntity.setAccountReceivableTurnover(xueqiuList.getAccountReceivableTurnover().get(0));
            financialReportEntity.setAccountsPayableTurnover(xueqiuList.getAccountsPayableTurnover().get(0));
            financialReportEntity.setCurrentAssetTurnoverRate(xueqiuList.getCurrentAssetTurnoverRate().get(0));
            financialReports.add(financialReportEntity);
        }
        this.insertAll(financialReports);

        LOG.error("下载季报数据成功，股票代码为：" + stockEntity.getSymbol());
    }

}