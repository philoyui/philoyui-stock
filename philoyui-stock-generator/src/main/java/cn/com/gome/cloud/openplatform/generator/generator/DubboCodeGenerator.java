package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class DubboCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "dubbo_service.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "dubbo/"+ entity.getName() + "DubboService.java";
    }
}
