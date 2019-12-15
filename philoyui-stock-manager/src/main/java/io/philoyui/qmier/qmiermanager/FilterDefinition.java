package io.philoyui.qmier.qmiermanager;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;
import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;

import java.io.Serializable;

@DescEntity(name = "筛选条件", domainName = "filter_definition")
public class FilterDefinition implements Serializable {

    @Desc(name = "ID")
    private Long id;

    @Desc(name = "唯一标识")
    private String identifier;

    @Desc(name = "名字")
    private String name;

    @Desc(name = "描述")
    private String description;

    @Desc(name = "是否启用")
    private boolean enable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
