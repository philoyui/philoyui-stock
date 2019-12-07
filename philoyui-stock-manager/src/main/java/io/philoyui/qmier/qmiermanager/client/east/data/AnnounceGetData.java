package io.philoyui.qmier.qmiermanager.client.east.data;

import com.google.gson.annotations.SerializedName;
import io.philoyui.qmier.qmiermanager.client.east.vo.AnnounceStockInfo;
import io.philoyui.qmier.qmiermanager.client.east.vo.AnnounceTypeInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AnnounceGetData implements Serializable {

    /**
     * 公告标题
     */
    @SerializedName("NOTICETITLE")
    private String noticeTitle;

    /**
     * 公告股票信息
     */
    @SerializedName("CDSY_SECUCODES")
    private List<AnnounceStockInfo> announceStockInfoList;

    /**
     * 公告类型
     */
    @SerializedName("ANN_RELCOLUMNS")
    private List<AnnounceTypeInfo> announceTypeInfoList;

    /**
     * 发布时间
     */
    @SerializedName("EUTIME")
    private Date publishTime;

    /**
     * 报告链接
     */
    @SerializedName("Url")
    private String url;

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public List<AnnounceStockInfo> getAnnounceStockInfoList() {
        return announceStockInfoList;
    }

    public void setAnnounceStockInfoList(List<AnnounceStockInfo> announceStockInfoList) {
        this.announceStockInfoList = announceStockInfoList;
    }

    public List<AnnounceTypeInfo> getAnnounceTypeInfoList() {
        return announceTypeInfoList;
    }

    public void setAnnounceTypeInfoList(List<AnnounceTypeInfo> announceTypeInfoList) {
        this.announceTypeInfoList = announceTypeInfoList;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
