package io.philoyui.stock.client.xueqiu;

import java.io.Serializable;

public class XueqiuData implements Serializable {

    private XueqiuList[] list;

    public XueqiuList[] getList() {
        return list;
    }

    public void setList(XueqiuList[] list) {
        this.list = list;
    }

}
