package io.philoyui.qmier.qmiermanager.client.xueqiu.request;

import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.FinancialReportResponse;

import java.util.HashMap;
import java.util.Map;

public class FinancialReportRequest implements XueQiuRequest<FinancialReportResponse> {

    private String symbol;

    private boolean isDetail = true;

    /**
     * 排序顺序
     */
    private int count = 1000;

    @Override
    public Map<String, String> getMapParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("symbol",symbol);
        parameters.put("is_detail",String.valueOf(isDetail));
        parameters.put("count",String.valueOf(count));
        return parameters;
    }

    @Override
    public String getMethodUrl() {
        return "https://stock.xueqiu.com/v5/stock/finance/cn/indicator.json";
    }

    @Override
    public Class<FinancialReportResponse> getResponseClass() {
        return FinancialReportResponse.class;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
