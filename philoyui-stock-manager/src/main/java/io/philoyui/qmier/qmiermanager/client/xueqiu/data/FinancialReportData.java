package io.philoyui.qmier.qmiermanager.client.xueqiu.data;

import io.philoyui.qmier.qmiermanager.client.xueqiu.XueqiuList;

import java.io.Serializable;

public class FinancialReportData implements Serializable {

    private XueqiuList[] list;

    public XueqiuList[] getList() {
        return list;
    }

    public void setList(XueqiuList[] list) {
        this.list = list;
    }
}
