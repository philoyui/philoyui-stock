package io.philoyui.qmier.qmiermanager;

public class PageServiceCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "page_service.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "page/"+ entity.getName() + "PageService.java";
    }
}
