package io.philoyui.qmier.qmiermanager.client.xueqiu.request;

import io.philoyui.qmier.qmiermanager.client.xueqiu.XueQiuRequest;
import io.philoyui.qmier.qmiermanager.client.xueqiu.response.AnnualReportResponse;

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
    private String orderby;

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
        return null;
    }

    @Override
    public String getMethodUrl() {
        return "/service/v5/stock/screener/quote/list";
    }

    @Override
    public Class<AnnualReportResponse> getResponseClass() {
        return AnnualReportResponse.class;
    }
}
