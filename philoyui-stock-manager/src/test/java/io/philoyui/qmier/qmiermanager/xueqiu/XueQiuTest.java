package io.philoyui.qmier.qmiermanager.xueqiu;

import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClient;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuClientImpl;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueqiuList;
import io.philoyui.qmier.qmiermanager.client.xueqiu.request.FinancialReportRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.FinancialReportResponse;
import org.junit.Test;

public class XueQiuTest {

    @Test
    public void test_fetch(){
        XueQiuClient client = new XueQiuClientImpl();

        FinancialReportRequest request = new FinancialReportRequest();
        request.setSymbol("");
        FinancialReportResponse response = client.execute(request);

        for (XueqiuList xueqiuList : response.getData().getList()) {

        }

    }

}
