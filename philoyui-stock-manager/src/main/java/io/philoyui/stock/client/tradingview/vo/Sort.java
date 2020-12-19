/**
 * Copyright 2019 bejson.com
 */
package io.philoyui.stock.client.tradingview.vo;

/**
 * Auto-generated: 2019-10-29 22:5:36
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Sort {

    private String sortBy;

    private String sortOrder;

    public Sort() {
    }

    public Sort(String sortBy, String sortOrder) {
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getSortOrder() {
        return sortOrder;
    }

}