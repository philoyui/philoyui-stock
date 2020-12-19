package io.philoyui.stock.client.xueqiu.response;

import io.philoyui.stock.client.xueqiu.XueQiuResponse;
import io.philoyui.stock.client.xueqiu.data.FinancialReportData;

public class FinancialReportResponse implements XueQiuResponse {

    private FinancialReportData data;

    public FinancialReportData getData() {
        return data;
    }

    public void setData(FinancialReportData data) {
        this.data = data;
    }
}
