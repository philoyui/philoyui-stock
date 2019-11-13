package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class DubboImplCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "dubbo_service_impl.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "dubbo/impl/"+ entity.getName() + "DubboServiceImpl.java";
    }
}
