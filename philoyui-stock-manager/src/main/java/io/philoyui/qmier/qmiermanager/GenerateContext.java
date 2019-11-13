package io.philoyui.qmier.qmiermanager;

import com.google.common.collect.Lists;

import java.util.List;

public class GenerateContext {

    /**
     * 包名
     */
    private String basePackage;

    /**
     * entity类
     */
    private List<Class> entityClasses;

    private String entityName;

    /**
     * entity类存在的文件夹名字，默认为entity
     */
    private String entityFolder = "entity";

    /**
     * 需要的Folder
     */
    private List<String> neededFolders = Lists.newArrayList();

    /**
     * 代码生成器
     */
    private List<CodeGenerator> codeGenerators = Lists.newArrayList();

    /**
     * 实体对象列表
     */
    private List<GenerateEntity> entities = Lists.newArrayList();

    /**
     * 生成源码的基本路径
     */
    private String basePath;

    public static GenerateContext construct(PageCodeRequest request) {
        GenerateContext generateContext = new GenerateContext();
        for (CodeTemplate codeTemplate : request.getCodeTemplates()) {
            generateContext.addFolder(codeTemplate.getFolder());
            generateContext.addCodeGenerator(CodeGenerators.select(codeTemplate));
        }
        for (Class entityClass : request.getEntityClasses()) {
            generateContext.addEntity(GenerateEntity.constructFrom(entityClass));
        }
        generateContext.basePackage = request.getBasePackage();
        generateContext.basePath=request.getBasePath();
        return generateContext;
    }

    private void addEntity(GenerateEntity generateEntity) {
        entities.add(generateEntity);
    }

    private void addCodeGenerator(CodeGenerator codeGenerator) {
        codeGenerators.add(codeGenerator);
    }

    private void addFolder(String folder) {
        neededFolders.add(folder);
    }

    public String getBasePackage() {
        return basePackage;
    }

    public List<String> getNeededFolders() {
        return neededFolders;
    }

    public List<GenerateEntity> getEntities() {
        return entities;
    }

    public String getEntityFolder() {
        return entityFolder;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public List<CodeGenerator> getCodeGenerators() {
        return codeGenerators;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }
}
