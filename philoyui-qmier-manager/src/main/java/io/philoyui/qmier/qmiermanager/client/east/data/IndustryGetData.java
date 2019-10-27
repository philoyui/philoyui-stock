package io.philoyui.qmier.qmiermanager.client.east.data;

import io.philoyui.qmier.qmiermanager.client.east.diff.IndustryGetDiff;

import java.io.Serializable;

public class IndustryGetData implements Serializable {

    private int total;

    private IndustryGetDiff[] diff;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public IndustryGetDiff[] getDiff() {
        return diff;
    }

    public void setDiff(IndustryGetDiff[] diff) {
        this.diff = diff;
    }

}
