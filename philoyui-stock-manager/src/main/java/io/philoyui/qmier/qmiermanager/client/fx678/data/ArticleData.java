package io.philoyui.qmier.qmiermanager.client.fx678.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class ArticleData implements Serializable {

    /**
     * 文章ID
     */
    @SerializedName("article_id")
    private String articleId;



    /**
     * 文章标题
     */
    @SerializedName("article_title")
    private String articleTitle;

    /**
     * 创建时间 2019-11-02 21:44:49
     */
    @SerializedName("create_date")
    private Date createDate;

    /**
     * 版区ID
     */
    @SerializedName("category_id")
    private int categoryId;

    /**
     * 文章主图
     */
    @SerializedName("article_image")
    private String articleImage;

    /**
     * 文章序号
     */
    @SerializedName("article_order")
    private int articleOrder;

    /**
     * 时分
     */
    @SerializedName("pdate")
    private String pdate;

    @SerializedName("mdate")
    private String mdate;

    /**
     * 年月日
     */
    @SerializedName("ydate")
    private String ydate;

    /**
     * 分析师ID
     */
    @SerializedName("anal_id")
    private Long analId;

    /**
     * 分析师真名
     */
    @SerializedName("anal_real_name")
    private String analRealName;

    /**
     * 分析师头像
     */
    @SerializedName("anal_image")
    private String analImage;

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public int getArticleOrder() {
        return articleOrder;
    }

    public void setArticleOrder(int articleOrder) {
        this.articleOrder = articleOrder;
    }

    public String getPdate() {
        return pdate;
    }

    public void setPdate(String pdate) {
        this.pdate = pdate;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public Long getAnalId() {
        return analId;
    }

    public void setAnalId(Long analId) {
        this.analId = analId;
    }

    public String getAnalRealName() {
        return analRealName;
    }

    public void setAnalRealName(String analRealName) {
        this.analRealName = analRealName;
    }

    public String getAnalImage() {
        return analImage;
    }

    public void setAnalImage(String analImage) {
        this.analImage = analImage;
    }

    public String getYdate() {
        return ydate;
    }

    public void setYdate(String ydate) {
        this.ydate = ydate;
    }
}
