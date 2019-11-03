package io.philoyui.qmier.qmiermanager.controller;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.philoyui.qmier.qmiermanager.entity.StockEntity;
import io.philoyui.qmier.qmiermanager.service.StockService;
import io.philoyui.qmier.qmiermanager.service.StockYearDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 */
@RequestMapping
@Controller("/admin/stock")
public class StockYearDataController {

    @Autowired
    private StockYearDataService stockYearDataService;

    @Autowired
    private StockService stockService;

    private Gson gson = new GsonBuilder().create();

    @RequestMapping("/fetchAnnualReport")
    public ResponseEntity<String> fetchXueqiu(){

        List<StockEntity> stocks = stockService.list(SearchFilter.getDefault());
        for (StockEntity stock : stocks) {
            handleSave(stock.getCode());
        }
        return ResponseEntity.ok("success");

    }

    private void handleSave(String codeWithMark) {
//        try {
//            CloseableHttpClient httpClient = HttpClients.createDefault();
//            HttpGet httpGet = new HttpGet("https://stock.xueqiu.com/v5/stock/finance/cn/indicator.json?symbol=" + codeWithMark + "&type=Q4&is_detail=true&count=100");
//            CloseableHttpResponse response = httpClient.execute(httpGet);
//            HttpEntity entity = response.getEntity();
//            String text = EntityUtils.toString(entity);
//            text = text.replaceAll("\"-\"", "0");
//            XueQiuResponse xueqiuBaseResponse = gson.fromJson(text, XueQiuesponse.class);
//            for (XueqiuList xueqiuList : xueqiuBaseResponse.getData().getList()) {
//                StockYearDataEntity stockYearData = new StockYearDataEntity();
//                stockYearData.setCode(codeWithMark);
//                stockYearData.setYear(xueqiuList.getReportName().substring(0,4));
//                stockYearData.setRoe(xueqiuList.getAvgRoe().get(0));
//                stockYearData.setEarningsPerShare(xueqiuList.getNpPerShare().get(0));
//                stockYearData.setDebtRatio(xueqiuList.getAssetLiabRatio().get(0));
//                stockYearData.setReportName(xueqiuList.getReportName());
//                stockYearData.setNpPerShare(xueqiuList.getNpPerShare().get(0));
//                stockYearData.setOperateCashFlowPs(xueqiuList.getOperateCashFlowPs().get(0));
//                stockYearData.setCapitalReserve(xueqiuList.getCapitalReserve().get(0));
//                stockYearData.setUndistriProfitPs(xueqiuList.getUndistriProfitPs().get(0));
//                stockYearData.setNetInterestOfTotalAssets(xueqiuList.getNetInterestOfTotalAssets().get(0));
//                stockYearData.setNetSellingRate(xueqiuList.getNetSellingRate().get(0));
//                stockYearData.setGrossSellingRate(xueqiuList.getGrossSellingRate().get(0));
//                stockYearData.setTotalRevenue(xueqiuList.getTotalRevenue().get(0));
//                stockYearData.setOperatingIncomeYoy(xueqiuList.getOperatingIncomeYoy().get(0));
//                stockYearData.setNetProfitAtsopc(xueqiuList.getNetProfitAtsopc().get(0));
//                stockYearData.setNetProfitAtsopcYoy(xueqiuList.getNetProfitAtsopcYoy().get(0));
//                stockYearData.setNetProfitAfterNrgalAtsolc(xueqiuList.getNetProfitAfterNrgalAtsolc().get(0));
//                stockYearData.setNpAtsopcNrgalYoy(xueqiuList.getNpAtsopcNrgalYoy().get(0));
//                stockYearData.setOreDlt(xueqiuList.getOreDlt().get(0));
//                stockYearData.setCurrentRatio(xueqiuList.getCurrentRatio().get(0));
//                stockYearData.setQuickRatio(xueqiuList.getQuickRatio().get(0));
//                stockYearData.setEquityMultiplier(xueqiuList.getEquityMultiplier().get(0));
//                stockYearData.setEquityRatio(xueqiuList.getEquityRatio().get(0));
//                stockYearData.setHolderEquity(xueqiuList.getHolderEquity().get(0));
//                stockYearData.setNcfFromOaToTotalLiab(xueqiuList.getNcfFromOaToTotalLiab().get(0));
//                stockYearData.setInventoryTurnoverDays(xueqiuList.getInventoryTurnoverDays().get(0));
//                stockYearData.setReceivableTurnoverDays(xueqiuList.getReceivableTurnoverDays().get(0));
//                stockYearData.setAccountsPayableTurnoverDays(xueqiuList.getAccountsPayableTurnoverDays().get(0));
//                stockYearData.setCashCycle(xueqiuList.getCashCycle().get(0));
//                stockYearData.setOperatingCycle(xueqiuList.getOperatingCycle().get(0));
//                stockYearData.setTotalCapitalTurnover(xueqiuList.getTotalCapitalTurnover().get(0));
//                stockYearData.setInventoryTurnover(xueqiuList.getInventoryTurnover().get(0));
//                stockYearData.setAccountReceivableTurnover(xueqiuList.getAccountReceivableTurnover().get(0));
//                stockYearData.setAccountsPayableTurnover(xueqiuList.getAccountsPayableTurnover().get(0));
//                stockYearData.setCurrentAssetTurnoverRate(xueqiuList.getCurrentAssetTurnoverRate().get(0));
//                stockYearDataService.insert(stockYearData);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}
