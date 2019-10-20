package io.philoyui.qmier.qmiermanager.entity;

import io.philoyui.qmier.qmiermanager.entity.enu.ArticleStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 文章地址队列
 */
@Entity
public class ArticleRecordEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 网站
     */
    private Long siteId;

    /**
     *
     */
    private String title;

    /**
     * 网站分类ID
     */
    private Long siteCategoryId;

    /**
     * 详情页ID
     */
    private String detailId;

    /**
     * 阅读数
     */
    private Integer readCount;

    /**
     * 回复数
     */
    private Integer replyCount;

    /**
     * 分页数
     */
    private Integer pageCount;

    /**
     * 详细页地址
     */
    private String detailUrl;

    /**
     * 抓取状态，待抓取-> 抓取中 —> 已抓取
     */
    private ArticleStatus status;

    /**
     * 作者
     */
    private String author;


    /**
     * 正文
     */
    @Lob
    private String content;

    /**
     * 进入队列时间
     */
    private Date createdTime;

    /**
     * 处理时间
     */
    private Date processTime;

    /**
     * 图片地址
     */
    private String imagePaths;

    /**
     * 姓名
     */
    private String qmierName;

    /**
     * 城市
     */
    private String city;

    /**
     * 区域
     */
    private String region;

    /**
     * 评论
     */
    @Lob
    private String comments;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }

    public Long getSiteCategoryId() {
        return siteCategoryId;
    }

    public void setSiteCategoryId(Long siteCategoryId) {
        this.siteCategoryId = siteCategoryId;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImagePaths() {
        return imagePaths;
    }

    public void setImagePaths(String imagePaths) {
        this.imagePaths = imagePaths;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    public String getQmierName() {
        return qmierName;
    }

    public void setQmierName(String qmierName) {
        this.qmierName = qmierName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}

