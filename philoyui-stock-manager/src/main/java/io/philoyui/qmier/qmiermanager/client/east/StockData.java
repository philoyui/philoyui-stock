package io.philoyui.qmier.qmiermanager.client.east;

import io.philoyui.qmier.qmiermanager.entity.StockEntity;

import java.io.Serializable;

public class StockData implements Serializable {

    private int total;

    private StockEntity[] diff;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public StockEntity[] getDiff() {
        return diff;
    }

    public void setDiff(StockEntity[] diff) {
        this.diff = diff;
    }

}
