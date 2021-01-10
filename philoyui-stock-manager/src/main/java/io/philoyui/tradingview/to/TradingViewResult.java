package io.philoyui.tradingview.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradingViewResult implements Serializable {

    private int totalCount;

    private List<DataBean> data = new ArrayList<>();

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String s;

        private List<String> d;

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public List<String> getD() {
            return d;
        }

        public void setD(List<String> d) {
            this.d = d;
        }
    }
}
