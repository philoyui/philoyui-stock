package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class VoCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "vo.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "vo/"+ entity.getName() + ".java";
    }
}
