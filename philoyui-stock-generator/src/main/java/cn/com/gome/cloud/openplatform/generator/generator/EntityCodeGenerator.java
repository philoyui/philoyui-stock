package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class EntityCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "entity.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "entity/"+ entity.getName() + "Entity.java";
    }
}
