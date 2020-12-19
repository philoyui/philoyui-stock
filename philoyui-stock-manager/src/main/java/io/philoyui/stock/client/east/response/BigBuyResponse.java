package io.philoyui.stock.client.east.response;

import io.philoyui.stock.client.east.data.BigBuyData;

import java.io.Serializable;
import java.util.List;

public class BigBuyResponse implements Serializable {

    private int pages;

    private List<BigBuyData> data;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<BigBuyData> getData() {
        return data;
    }

    public void setData(List<BigBuyData> data) {
        this.data = data;
    }
}
