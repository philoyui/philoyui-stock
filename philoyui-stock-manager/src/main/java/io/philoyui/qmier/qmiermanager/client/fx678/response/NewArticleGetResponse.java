package io.philoyui.qmier.qmiermanager.client.fx678.response;

import io.philoyui.qmier.qmiermanager.client.fx678.Fx678Response;
import io.philoyui.qmier.qmiermanager.client.fx678.data.ArticleData;

public class NewArticleGetResponse implements Fx678Response {

    private Integer code;

    private String message;

    private ArticleData[] data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArticleData[] getData() {
        return data;
    }

    public void setData(ArticleData[] data) {
        this.data = data;
    }
}
