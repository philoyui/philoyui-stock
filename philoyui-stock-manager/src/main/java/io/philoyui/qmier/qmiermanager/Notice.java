package io.philoyui.qmier.qmiermanager;


import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;
import io.philoyui.qmier.qmiermanager.entity.enu.NoticeType;

import java.util.Date;

@DescEntity(name = "通知", domainName = "notice")
public class Notice {

    @Desc(name = "ID")
    private Long id;

    @Desc(name="标题")
    private String title;

    @Desc(name = "股票代码")
    private String symbol;

    @Desc(name="正文")
    private String content;

    @Desc(name = "创建时间")
    private Date createTime;

    @Desc(name = "通知类型")
    private NoticeType noticeType;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public NoticeType getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(NoticeType noticeType) {
        this.noticeType = noticeType;
    }
}
