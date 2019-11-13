package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class EventHandlerCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "event_handler.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "eventhandler/"+ entity.getName() + "EventHandler.java";
    }
}
