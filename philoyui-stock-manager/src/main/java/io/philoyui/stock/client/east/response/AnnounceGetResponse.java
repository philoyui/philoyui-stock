package io.philoyui.stock.client.east.response;

import com.google.gson.annotations.SerializedName;
import io.philoyui.stock.client.east.EastMoneyResponse;
import io.philoyui.stock.client.east.data.AnnounceGetData;

import java.util.List;

public class AnnounceGetResponse implements EastMoneyResponse {

    private List<AnnounceGetData> data;

    @SerializedName("TotalCount")
    private int totalCount;

    private int pages;

    private int rc;

    private String me;

    @SerializedName("dataUrl")
    private String dataUrl;

    public List<AnnounceGetData> getData() {
        return data;
    }

    public void setData(List<AnnounceGetData> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}
