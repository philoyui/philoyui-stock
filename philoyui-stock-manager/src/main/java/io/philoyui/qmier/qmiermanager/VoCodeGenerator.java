package io.philoyui.qmier.qmiermanager;

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
