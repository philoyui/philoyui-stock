package io.philoyui.qmier.qmiermanager;

public class ControllerCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "controller.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "controller/"+ entity.getName() + "Controller.java";
    }
}
