package io.philoyui.qmier.qmiermanager.client.east.response;

import io.philoyui.qmier.qmiermanager.client.east.EastMoneyResponse;
import io.philoyui.qmier.qmiermanager.client.east.data.IndustryGetData;

public class IndustryGetResponse implements EastMoneyResponse {

    private IndustryGetData data;

    public IndustryGetData getData() {
        return data;
    }

    public void setData(IndustryGetData data) {
        this.data = data;
    }

}
