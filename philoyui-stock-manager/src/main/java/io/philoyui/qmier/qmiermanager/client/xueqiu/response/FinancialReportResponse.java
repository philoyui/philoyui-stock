package io.philoyui.qmier.qmiermanager.client.xueqiu.response;

import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuResponse;
import io.philoyui.qmier.qmiermanager.client.xueqiu.data.FinancialReportData;

public class FinancialReportResponse implements XueQiuResponse {

    private FinancialReportData data;

    public FinancialReportData getData() {
        return data;
    }

    public void setData(FinancialReportData data) {
        this.data = data;
    }
}
