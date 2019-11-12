package io.philoyui.qmier.qmiermanager;

public class ServiceCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "service.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "service/" +entity.getName() + "Service.java";
    }
}
