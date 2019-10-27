package io.philoyui.qmier.qmiermanager.client.east.response;

import io.philoyui.qmier.qmiermanager.client.east.EastMoneyResponse;
import io.philoyui.qmier.qmiermanager.client.east.data.ConceptGetData;

public class ConceptGetResponse implements EastMoneyResponse {

    private ConceptGetData data;

    public ConceptGetData getData() {
        return data;
    }

    public void setData(ConceptGetData data) {
        this.data = data;
    }
}
