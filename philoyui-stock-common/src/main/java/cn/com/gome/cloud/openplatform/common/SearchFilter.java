package cn.com.gome.cloud.openplatform.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangyu-ds on 2016/9/18.
 */
public class SearchFilter implements Serializable {

    private List<SimpleExpression> simpleExpressions = new ArrayList<>(5);

    private List<LogicalExpression> logicalExpressions = new ArrayList<>(5);

    private List<Order> orders = new ArrayList<>(5);

    private int pageNo = 0;

    private int pageSize = 20;

    public SearchFilter() {
        //
    }

    public static SearchFilter getDefault() {
        return new SearchFilter();
    }


    /**
     * @Title : add
     * @Description : 增加简单条件表达式
     */
    public void add(SimpleExpression simpleExpression) {
        if (simpleExpression != null) {
            simpleExpressions.add(simpleExpression);
        }
    }

    /**
     * @Title : add
     * @Description : 增加简单条件表达式
     */
    public void add(LogicalExpression logicalExpression) {
        if (logicalExpression != null) {
            logicalExpressions.add(logicalExpression);
        }
    }

    public void add(Order... input) {
        orders.addAll(Arrays.asList(input));
    }

    public static SearchFilter getPagedSearchFilter(int pageNo, int pageSize) {
        SearchFilter searchFilter = new SearchFilter();
        searchFilter.pageNo = pageNo;
        searchFilter.pageSize = pageSize;
        return searchFilter;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public int getPageNo() {
        return pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }


    public List<SimpleExpression> getSimpleExpressions() {
        return simpleExpressions;
    }

    public List<LogicalExpression> getLogicalExpressions() {
        return logicalExpressions;
    }

}
