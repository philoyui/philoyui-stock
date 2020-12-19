package io.philoyui.stock.client.east.response;

import io.philoyui.stock.client.east.EastMoneyResponse;
import io.philoyui.stock.client.east.data.ConceptGetData;

public class ConceptGetResponse implements EastMoneyResponse {

    private ConceptGetData data;

    public ConceptGetData getData() {
        return data;
    }

    public void setData(ConceptGetData data) {
        this.data = data;
    }
}
