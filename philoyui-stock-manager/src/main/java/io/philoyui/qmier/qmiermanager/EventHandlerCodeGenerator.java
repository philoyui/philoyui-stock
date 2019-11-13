package io.philoyui.qmier.qmiermanager;

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
