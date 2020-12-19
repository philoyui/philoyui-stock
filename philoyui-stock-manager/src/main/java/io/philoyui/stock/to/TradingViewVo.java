package io.philoyui.stock.to;

import com.google.common.collect.Lists;

import java.util.List;

public class TradingViewVo {

    private List<FilterBean> filter;
    private OptionsBean options = new OptionsBean("zh","PREV_BAR");
    private SymbolsBean symbols = new SymbolsBean();
    private List<String> columns;
    private SortBean sort = new SortBean("Recommend.All","desc");
    private List<Integer> range = Lists.newArrayList(0,150);

    public OptionsBean getOptions() {
        return options;
    }

    public void setOptions(OptionsBean options) {
        this.options = options;
    }

    public SymbolsBean getSymbols() {
        return symbols;
    }

    public void setSymbols(SymbolsBean symbols) {
        this.symbols = symbols;
    }

    public SortBean getSort() {
        return sort;
    }

    public void setSort(SortBean sort) {
        this.sort = sort;
    }

    public List<FilterBean> getFilter() {
        return filter;
    }

    public void setFilter(List<FilterBean> filter) {
        this.filter = filter;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<Integer> getRange() {
        return range;
    }

    public void setRange(List<Integer> range) {
        this.range = range;
    }

    public static class OptionsBean {

        private String data_restrictions = "PREV_BAR";
        private String lang = "zh";

        public OptionsBean(String lang, String data_restrictions) {
            this.lang = lang;
            this.data_restrictions = data_restrictions;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }

        public String getData_restrictions() {
            return data_restrictions;
        }

        public void setData_restrictions(String data_restrictions) {
            this.data_restrictions = data_restrictions;
        }

    }

    public static class SymbolsBean {

        private QueryBean query = new QueryBean();
        private List<?> tickers = Lists.newArrayList();

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public List<?> getTickers() {
            return tickers;
        }

        public void setTickers(List<?> tickers) {
            this.tickers = tickers;
        }

        public static class QueryBean {
            private List<?> types = Lists.newArrayList();

            public List<?> getTypes() {
                return types;
            }

            public void setTypes(List<?> types) {
                this.types = types;
            }
        }
    }

    public static class SortBean {

        private String sortBy;

        private String sortOrder;

        public SortBean(String sortBy, String sortOrder) {
            this.sortBy = sortBy;
            this.sortOrder = sortOrder;
        }

        public String getSortBy() {
            return sortBy;
        }

        public void setSortBy(String sortBy) {
            this.sortBy = sortBy;
        }

        public String getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(String sortOrder) {
            this.sortOrder = sortOrder;
        }
    }

    public static class FilterBean {

        private String left;
        private String operation;
        private Object right;

        public FilterBean(String left, String operation, Object right) {
            this.left = left;
            this.operation = operation;
            this.right = right;
        }

        public String getLeft() {
            return left;
        }

        public void setLeft(String left) {
            this.left = left;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public Object getRight() {
            return right;
        }

        public void setRight(Object right) {
            this.right = right;
        }
    }
}
