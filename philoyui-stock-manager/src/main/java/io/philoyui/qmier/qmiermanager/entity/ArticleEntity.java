package io.philoyui.qmier.qmiermanager.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class ArticleEntity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    /**
     * 网站标识
     */
    private String siteIdentifier;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文
     */
    @Lob
    private String content;

    /**
     * 创建日期
     */
    private Date createdTime;

    /**
     * 分析师
     */
    private String analyst;

    /**
     * 详细链接
     */
    private String detailUrl;

    /**
     * 预测
     */
    private String prediction;


    /**
     * 得分1-10
     */
    private int score;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSiteIdentifier() {
        return siteIdentifier;
    }

    public void setSiteIdentifier(String siteIdentifier) {
        this.siteIdentifier = siteIdentifier;
    }
}
