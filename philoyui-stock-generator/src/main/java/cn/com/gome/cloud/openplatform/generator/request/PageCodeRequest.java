package cn.com.gome.cloud.openplatform.generator.request;

import cn.com.gome.cloud.openplatform.generator.CodeTemplate;

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

    /**
     * 生成代码路径
     */
    private String basePath;

    /**
     * 应用名称
     */
    private String appName;

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

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppName() {
        return appName;
    }
}
