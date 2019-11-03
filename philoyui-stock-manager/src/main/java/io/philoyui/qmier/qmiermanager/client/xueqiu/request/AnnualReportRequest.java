package io.philoyui.qmier.qmiermanager.client.xueqiu.request;

import com.google.gson.annotations.SerializedName;
import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.AnnualReportResponse;

import java.util.HashMap;
import java.util.Map;

public class AnnualReportRequest implements XueQiuRequest<AnnualReportResponse> {

    //page=1&size=90&order=desc&orderby=percent&order_by=percent&market=CN&type=sh_sz&_=1571724324541

    private Integer page;

    private Integer size;

    /**
     * 排序顺序
     */
    private String order;

    /**
     * 按那个字段排序
     */
    @SerializedName("order_by")
    private String orderBy;

    /**
     * 市场
     */
    private String market;

    /**
     * 股票类型
     */
    private String type;

    @Override
    public Map<String, String> getMapParameters() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("page",String.valueOf(page));
        parameters.put("size",String.valueOf(size));
        parameters.put("order",order);
        parameters.put("order_by",orderBy);
        parameters.put("market",market);
        parameters.put("type",type);
        return parameters;
    }

    @Override
    public String getMethodUrl() {
        return "/service/v5/stock/screener/quote/list";
    }

    @Override
    public Class<AnnualReportResponse> getResponseClass() {
        return AnnualReportResponse.class;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
