package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.util.Date;

@DescEntity(name = "公告", domainName = "announce")
public class Announce {

    @Desc(name = "ID")
    private Long id;

    @Desc(name="关联网站ID")
    private String referId;

    @Desc(name="标题",filter = true)
    private String title;

    @Desc(name = "股票编码",filter = true)
    private String symbol;

    @Desc(name = "发布时间",filter = true)
    private Date publishTime;

    @Desc(name="详细页链接")
    private String detailUrl;

    @Desc(name="公告类型",filter = true)
    private String announceType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getAnnounceType() {
        return announceType;
    }

    public void setAnnounceType(String announceType) {
        this.announceType = announceType;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }
}
