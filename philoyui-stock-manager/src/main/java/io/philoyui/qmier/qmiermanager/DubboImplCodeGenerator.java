package io.philoyui.qmier.qmiermanager;

public class DubboImplCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "dubbo_impl.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "dubbo/impl/"+ entity.getName() + "DubboServiceImpl.java";
    }
}
