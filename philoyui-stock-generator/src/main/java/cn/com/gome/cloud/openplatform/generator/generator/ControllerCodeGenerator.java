package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

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
