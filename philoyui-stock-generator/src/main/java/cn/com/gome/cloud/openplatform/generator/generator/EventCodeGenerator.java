package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class EventCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "event.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "event/"+ entity.getName() + "Event.java";
    }
}
