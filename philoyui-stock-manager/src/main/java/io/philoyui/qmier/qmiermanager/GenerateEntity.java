package io.philoyui.qmier.qmiermanager;

import java.lang.reflect.Field;
import java.util.List;

public class GenerateEntity {

    private String name;

    private List<FieldConfig> fieldConfigs;

    public static GenerateEntity constructFrom(Class entityClass) {
        GenerateEntity generateEntity = new GenerateEntity();
        generateEntity.setName(entityClass.getSimpleName().replaceAll("Entity",""));
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
}
