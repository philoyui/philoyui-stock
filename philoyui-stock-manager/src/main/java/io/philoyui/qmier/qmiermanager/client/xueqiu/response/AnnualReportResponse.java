package io.philoyui.qmier.qmiermanager.client.xueqiu.response;

import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuResponse;
import io.philoyui.qmier.qmiermanager.client.xueqiu.data.AnnualReportData;

public class AnnualReportResponse implements XueQiuResponse {

    private AnnualReportData data;

    public AnnualReportData getData() {
        return data;
    }

    public void setData(AnnualReportData data) {
        this.data = data;
    }
}
