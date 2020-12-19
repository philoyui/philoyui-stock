package io.philoyui.stock.client.tradingview;

import com.google.common.collect.Lists;
import io.philoyui.stock.client.tradingview.vo.Filter;
import io.philoyui.stock.client.tradingview.vo.Options;
import io.philoyui.stock.client.tradingview.vo.Sort;
import io.philoyui.stock.client.tradingview.vo.Symbols;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradingViewFilter implements Serializable {

    private List<Filter> filter;

    private Options options;

    private Symbols symbols;

    private List<String> columns = Lists.newArrayList();

    private Sort sort = new Sort();

    private List<Integer> range = new ArrayList<>();

    public TradingViewFilter(int pageNo, int pageSize) {
        range.add(pageNo-1);
        range.add(pageSize);
        options = new Options("zh",
                "PREV_BAR",
                true);
        columns = Lists.newArrayList("name",
                "close",//收盘价
                "change",//涨跌幅
                "change_abs",//涨跌额
                "Recommend.All",//评级
                "volume",//量
                "market_cap_basic",//总市值
                "price_earnings_ttm",//市盈率
                "earnings_per_share_basic_ttm",
                "number_of_employees",//雇员
                "sector",//分类
                "description",//股票名
                "name",//code
                "type",
                "subtype",
                "update_mode",
                "pricescale",
                "minmov",
                "fractional",
                "minmove2");
    }

    public void setFilter(List<Filter> filter) {
        this.filter = filter;
    }

    public List<Filter> getFilter() {
        return filter;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public Options getOptions() {
        return options;
    }

    public void setSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    public Symbols getSymbols() {
        return symbols;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Sort getSort() {
        return sort;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void addSort(String sortBy, String sortOrder) {
        sort = new Sort(sortBy,sortOrder);
    }
}
