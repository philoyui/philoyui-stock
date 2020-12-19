package io.philoyui.stock.client.east.data;

import io.philoyui.stock.client.east.diff.ConceptGetDiff;

import java.io.Serializable;

public class ConceptGetData implements Serializable {

    private int total;

    private ConceptGetDiff[] diff;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ConceptGetDiff[] getDiff() {
        return diff;
    }

    public void setDiff(ConceptGetDiff[] diff) {
        this.diff = diff;
    }
}
