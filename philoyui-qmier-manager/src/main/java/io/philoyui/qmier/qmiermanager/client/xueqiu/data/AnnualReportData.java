package io.philoyui.qmier.qmiermanager.client.xueqiu.data;

import io.philoyui.qmier.qmiermanager.entity.stock.StockEntity;

import java.io.Serializable;

public class AnnualReportData implements Serializable {

    private StockEntity[] list;

    public StockEntity[] getList() {
        return list;
    }

    public void setList(StockEntity[] list) {
        this.list = list;
    }

}
