package io.philoyui.stock.client.east.request;

import com.google.common.collect.Maps;
import io.philoyui.stock.client.east.EastMoneyRequest;
import io.philoyui.stock.client.east.response.IndustryGetResponse;

import java.util.Map;

public class IndustryGetRequest implements EastMoneyRequest<IndustryGetResponse> {

    private int pageNo;

    private int pageSize;


    @Override
    public Map<String, String> getMapParameters() {
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("pn",String.valueOf(pageNo));
        parameters.put("pz",String.valueOf(pageSize));
        parameters.put("po","1");
        parameters.put("np","1");
        parameters.put("ut","bd1d9ddb04089700cf9c27f6f7426281");
        parameters.put("fltt","2");
        parameters.put("invt","2");
        parameters.put("fid","f3");
        parameters.put("fs","m:90+t:2");
        parameters.put("fields","f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f26,f22,f33,f11,f62,f128,f136,f115,f152,f124,f107,f104,f105,f140,f141,f207,f222");
        return parameters;
    }

    @Override
    public String getMethodUrl() {
        return "/api/qt/clist/get";
    }

    @Override
    public Class<IndustryGetResponse> getResponseClass() {
        return IndustryGetResponse.class;
    }

    @Override
    public String formatContent(String body) {
        return body;
    }


    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
