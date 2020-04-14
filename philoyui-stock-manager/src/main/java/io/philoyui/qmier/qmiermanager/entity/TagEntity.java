package io.philoyui.qmier.qmiermanager.entity;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
public class TagEntity implements Serializable {

    private Long id;

    private String tagName;

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
}
