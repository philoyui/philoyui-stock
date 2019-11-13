package io.philoyui.qmier.qmiermanager;

public class ServiceImplCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "service_impl.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "service/impl/"+ entity.getName() + "ServiceImpl.java";
    }
}
