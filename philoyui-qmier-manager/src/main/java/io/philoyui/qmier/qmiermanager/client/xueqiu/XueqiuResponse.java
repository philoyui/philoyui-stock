package io.philoyui.qmier.qmiermanager.client.xueqiu;

import java.io.Serializable;

public class XueqiuResponse implements Serializable {

    private XueqiuData data;

    public XueqiuData getData() {
        return data;
    }

    public void setData(XueqiuData data) {
        this.data = data;
    }

}


