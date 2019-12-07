package io.philoyui.qmier.qmiermanager.eastmoney;

import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClient;
import io.philoyui.qmier.qmiermanager.client.east.EastMoneyClientImpl;
import io.philoyui.qmier.qmiermanager.client.east.data.AnnounceGetData;
import io.philoyui.qmier.qmiermanager.client.east.request.AnnounceGetRequest;
import io.philoyui.qmier.qmiermanager.client.east.response.AnnounceGetResponse;
import org.junit.Test;

public class AnnounceGetTest {
    @Test
    public void test_get_announce() {
        EastMoneyClient client = new EastMoneyClientImpl();
        AnnounceGetRequest request = new AnnounceGetRequest();
        request.setPageNo(1);
        request.setPageSize(10);
        AnnounceGetResponse response = client.execute(request);
        for (AnnounceGetData data : response.getData()) {
            System.out.println(data.getNoticeTitle());
        }
    }
}
