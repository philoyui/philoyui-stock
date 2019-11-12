package io.philoyui.qmier.qmiermanager;

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
