package io.philoyui.qmier.qmiermanager.entity;

import cn.com.gome.page.domain.CategoryEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 商品分类
 */
@Entity
public class ArticleCategoryEntity implements CategoryEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long parentId;

    private String brief;

    private boolean enable;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean isRootElement() {
        return parentId == 0 ;
    }

    @Override
    public String getNodeId() {
        return String.valueOf(id);
    }

    @Override
    public String getParentNodeId() {
        return String.valueOf(parentId);
    }

    @Override
    public String getNodeName() {
        return name;
    }
}
