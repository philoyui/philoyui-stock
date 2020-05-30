package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.io.Serializable;
import java.util.Date;

@DescEntity(name = "标签股票", domainName = "tag_stock")
public class TagStock implements Serializable {

    @Desc(name = "ID")
    private Long id;

    @Desc(name = "编码")
    private String symbol;

    @Desc(name = "标签名称",filter = true)
    private String tagName;

    /**
     * 创建时间
     */
    @Desc(name = "创建时间",filter = true,order = true)
    private Date createdTime;

    @Desc(name = "时间标识")
    private String dayString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getDayString() {
        return dayString;
    }

    public void setDayString(String dayString) {
        this.dayString = dayString;
    }
}
