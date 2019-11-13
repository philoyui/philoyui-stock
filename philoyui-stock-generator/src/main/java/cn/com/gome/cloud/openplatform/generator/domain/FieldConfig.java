package cn.com.gome.cloud.openplatform.generator.domain;

import cn.com.gome.cloud.openplatform.generator.anno.Desc;

import java.lang.reflect.Field;

public class FieldConfig {

    private String name;

    private String type;
    /**
     * 描述
     */
    private String desc;

    /**
     * 是否必须
     */
    private boolean required;

    /**
     * 是否参与排序
     */
    private boolean order;

    /**
     * 是否参与筛选
     */
    private boolean filter;

    /**
     * 是否可以编辑
     */
    private boolean writable;

    /**
     * 指定类型
     */
    private String customType;

    public static FieldConfig constructForm(Field field) {
        FieldConfig fieldConfig = new FieldConfig();
        fieldConfig.name = field.getName();
        fieldConfig.type = field.getType().getSimpleName();

        if(field.isAnnotationPresent(Desc.class)){
            Desc desc = field.getDeclaredAnnotation(Desc.class);
            fieldConfig.desc = desc.name();
            fieldConfig.customType = desc.type();
            fieldConfig.filter = desc.filter();
            fieldConfig.order = desc.order();
            fieldConfig.required = desc.require();
            fieldConfig.writable = desc.writable();
        }
        return fieldConfig;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean isOrder() {
        return order;
    }

    public void setOrder(boolean order) {
        this.order = order;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }

    public boolean isWritable() {
        return writable;
    }

    public void setWritable(boolean writable) {
        this.writable = writable;
    }

    public String getCustomType() {
        return customType;
    }

    public void setCustomType(String customType) {
        this.customType = customType;
    }
}
