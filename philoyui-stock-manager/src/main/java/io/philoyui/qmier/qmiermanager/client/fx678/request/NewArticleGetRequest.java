package io.philoyui.qmier.qmiermanager.client.fx678.request;

import com.google.common.collect.Maps;
import io.philoyui.qmier.qmiermanager.client.fx678.Fx678Request;
import io.philoyui.qmier.qmiermanager.client.fx678.response.NewArticleGetResponse;

import java.util.Map;

public class NewArticleGetRequest implements Fx678Request<NewArticleGetResponse> {

    private String categoryId;

    private int pageNo;

    private int pageSize;

    @Override
    public Map<String, String> getMapParameters() {
        Map<String, String> parameters = Maps.newHashMap();
        parameters.put("category_id",categoryId);
        parameters.put("pageSize",String.valueOf(pageSize));
        parameters.put("page",String.valueOf(pageNo));
        return parameters;
    }

    @Override
    public String getMethodUrl() {
        return "https://pinglun.fx678.com/Ajax/changeIndexNewsByCategory";
    }

    @Override
    public Class<NewArticleGetResponse> getResponseClass() {
        return NewArticleGetResponse.class;
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

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
