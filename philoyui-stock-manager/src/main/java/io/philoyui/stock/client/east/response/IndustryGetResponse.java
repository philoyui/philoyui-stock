package io.philoyui.stock.client.east.response;

import io.philoyui.stock.client.east.EastMoneyResponse;
import io.philoyui.stock.client.east.data.IndustryGetData;

public class IndustryGetResponse implements EastMoneyResponse {

    private IndustryGetData data;

    public IndustryGetData getData() {
        return data;
    }

    public void setData(IndustryGetData data) {
        this.data = data;
    }

}
