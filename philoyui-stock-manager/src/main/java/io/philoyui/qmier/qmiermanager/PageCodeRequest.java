package io.philoyui.qmier.qmiermanager;

import java.util.List;

public class PageCodeRequest {

    /**
     * 包名
     */
    private String basePackage;

    /**
     * entity类
     */
    private List<Class> entityClasses;

    /**
     * 代码模板
     */
    private CodeTemplate[] codeTemplates;
    private String basePath;

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public List<Class> getEntityClasses() {
        return entityClasses;
    }

    public void setEntityClasses(List<Class> entityClasses) {
        this.entityClasses = entityClasses;
    }

    public void setCodeTemplates(CodeTemplate... codeTemplates) {
        this.codeTemplates = codeTemplates;
    }

    public CodeTemplate[] getCodeTemplates() {
        return codeTemplates;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBasePath() {
        return basePath;
    }
}
