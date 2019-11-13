package io.philoyui.qmier.qmiermanager;

public class DubboCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "dubbo.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "dubbo/"+ entity.getName() + "DubboService.java";
    }
}
