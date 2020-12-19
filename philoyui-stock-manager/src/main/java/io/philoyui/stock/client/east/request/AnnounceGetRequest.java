package io.philoyui.stock.client.east.request;

import io.philoyui.stock.client.east.EastMoneyRequest;
import io.philoyui.stock.client.east.response.AnnounceGetResponse;

import java.util.HashMap;
import java.util.Map;

public class AnnounceGetRequest implements EastMoneyRequest<AnnounceGetResponse> {

    private Integer pageNo;

    private Integer pageSize;

    private String StockCode;

    private Integer FirstNodeType = 0;

    private Integer CodeType = 1;

    private String jsObj = "NpXIveac";

    private String Time;


    @Override
    public Map<String, String> getMapParameters() {
        Map<String,String> parameters = new HashMap<>();
        parameters.put("PageIndex",String.valueOf(pageNo));
        parameters.put("PageSize",String.valueOf(pageSize));
        parameters.put("rt","52523192");
        parameters.put("StockCode","");
        parameters.put("FirstNodeType","0");
        parameters.put("CodeType","1");
        parameters.put("jsObj","NpXIveac");
        parameters.put("SecNodeType","0");
        parameters.put("Time","");
        return parameters;
    }

    @Override
    public String getMethodUrl() {
        return "http://data.eastmoney.com/notices/getdata.ashx";
    }

    @Override
    public Class<AnnounceGetResponse> getResponseClass() {
        return AnnounceGetResponse.class;
    }

    @Override
    public String formatContent(String body) {

        String content = body.replaceAll("var NpXIveac = ", "");

        return content.substring(0,content.length()-1);
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
