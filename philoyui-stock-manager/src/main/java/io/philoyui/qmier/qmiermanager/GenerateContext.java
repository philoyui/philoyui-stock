package io.philoyui.qmier.qmiermanager;

import java.util.List;

public class GenerateContext {

    /**
     * 包名
     */
    private String basePath;

    /**
     * entity类
     */
    private List<Class> entityClasses;

    private String entityName;

    public static GenerateContext construct(PageCodeRequest request) {
        return new GenerateContext();
    }

    public String getBasePath() {
        return "";
    }

    public List<String> getNeededFolders() {
        return null;
    }

    public List<GenerateEntity> getEntities() {
        return null;
    }

    public String getEntityFolder() {
        return null;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<GenerateFieldType> getFieldTypes() {
        return null;
    }
}
