package io.philoyui.qmier.qmiermanager.client.xueqiu.data;

import io.philoyui.qmier.qmiermanager.client.xueqiu.vo.AnnualReportList;

import java.io.Serializable;

public class AnnualReportData implements Serializable {

    private AnnualReportList list;

    public AnnualReportList getList() {
        return list;
    }

    public void setList(AnnualReportList list) {
        this.list = list;
    }

}
