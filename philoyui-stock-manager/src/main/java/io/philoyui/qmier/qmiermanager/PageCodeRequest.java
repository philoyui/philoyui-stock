package io.philoyui.qmier.qmiermanager;

import java.util.List;

public class PageCodeRequest {

    /**
     * 包名
     */
    private String basePath;

    /**
     * entity类
     */
    private List<Class> entityClasses;

    /**
     * 代码模板
     */
    private CodeTemplate[] codeTemplates;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String packageName) {
        this.basePath = packageName;
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
}
