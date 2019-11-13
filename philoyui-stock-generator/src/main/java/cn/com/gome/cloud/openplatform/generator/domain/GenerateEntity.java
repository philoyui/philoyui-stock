package cn.com.gome.cloud.openplatform.generator.domain;

import cn.com.gome.cloud.openplatform.generator.anno.DescEntity;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.List;

public class GenerateEntity {

    private String name;

    private String chineseName;

    private String domainName;

    private List<FieldConfig> fieldConfigs = Lists.newArrayList();

    public static GenerateEntity constructFrom(Class<?> entityClass) {
        GenerateEntity generateEntity = new GenerateEntity();
        generateEntity.setName(entityClass.getSimpleName().replaceAll("Entity",""));
        DescEntity descEntity = entityClass.getAnnotation(DescEntity.class);
        generateEntity.setChineseName(descEntity.name());
        generateEntity.setDomainName(descEntity.domainName());
        Field[] declaredFields = entityClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            generateEntity.addFieldConfigs(FieldConfig.constructForm(declaredField));
        }
        return generateEntity;
    }

    private void addFieldConfigs(FieldConfig fieldConfig) {
        fieldConfigs.add(fieldConfig);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FieldConfig> getFieldConfigs() {
        return fieldConfigs;
    }

    public void setFieldConfigs(List<FieldConfig> fieldConfigs) {
        this.fieldConfigs = fieldConfigs;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}
