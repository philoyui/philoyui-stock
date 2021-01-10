package io.philoyui.tradingview.to;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class TradingViewFilter {

    private TradingViewVo tradingViewVo = new TradingViewVo();

    private List<TradingViewVo.FilterBean> filterBeanList = new ArrayList<>();

    public TradingViewFilter(int pageNo, int pageSize) {
        tradingViewVo.setRange(Lists.newArrayList(pageNo,pageSize));
    }

    public void addFilter(String left, String operation) {
        filterBeanList.add(new TradingViewVo.FilterBean(left,operation,null));
    }

    public void addFilter(String left, String operation,Object right) {
        filterBeanList.add(new TradingViewVo.FilterBean(left,operation,right));
    }

    public void addColumns(String... columns) {
        tradingViewVo.setColumns(Lists.newArrayList(columns));
    }

    public TradingViewVo build() {
        tradingViewVo.setFilter(filterBeanList);
        return tradingViewVo;
    }
}
