package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class ServiceImplCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "service_impl.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "service/impl/"+ entity.getName() + "ServiceImpl.java";
    }
}
