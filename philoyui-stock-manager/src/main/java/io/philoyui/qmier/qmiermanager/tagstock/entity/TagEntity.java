package io.philoyui.qmier.qmiermanager.tagstock.entity;

import io.philoyui.qmier.qmiermanager.entity.enu.IntervalType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TagEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tagName;

    private IntervalType intervalType;

    private Integer last1Score = 0;

    private Integer last2Score = 0;

    private Integer last3Score = 0;

    private Date lastExecuteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }

    public Integer getLast1Score() {
        return last1Score == null ?0:last1Score;
    }

    public void setLast1Score(Integer last1Score) {
        this.last1Score = last1Score;
    }

    public Integer getLast2Score() {
        return last2Score == null ?0:last2Score;
    }

    public void setLast2Score(Integer last2Score) {
        this.last2Score = last2Score;
    }

    public Integer getLast3Score() {
        return last3Score == null ?0:last3Score;
    }

    public void setLast3Score(Integer last3Score) {
        this.last3Score = last3Score;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(IntervalType intervalType) {
        this.intervalType = intervalType;
    }
}
