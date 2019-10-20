package io.philoyui.qmier.qmiermanager.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 网站地址
 */
@Entity
public class SiteCategoryEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 网站名称
     */
    private String name;

    /**
     * 城市
     */
    private String city;

    /**
     * 网站ID
     */
    private Long siteId;

    /**
     * 列表页地址
     */
    private String siteListUrl;

    /**
     * 上次抓取时间
     */
    private Date lastProcessTime;

    /**
     * 是否开启
     */
    private boolean enable;

    /**
     * 总页码数
     */
    private Integer totalPageNum;


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteListUrl() {
        return siteListUrl;
    }

    public void setSiteListUrl(String siteListUrl) {
        this.siteListUrl = siteListUrl;
    }

    public Date getLastProcessTime() {
        return lastProcessTime;
    }

    public void setLastProcessTime(Date lastProcessTime) {
        this.lastProcessTime = lastProcessTime;
    }

    public Integer getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(Integer totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
