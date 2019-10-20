package io.philoyui.qmier.qmiermanager.client;

import java.io.Serializable;

public class StockResponse implements Serializable {

    private StockData data;

    public StockData getData() {
        return data;
    }

    public void setData(StockData data) {
        this.data = data;
    }
}
