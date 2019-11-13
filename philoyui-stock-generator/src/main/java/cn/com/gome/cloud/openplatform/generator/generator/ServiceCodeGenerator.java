package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

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
