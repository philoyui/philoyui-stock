package io.philoyui.qmier.qmiermanager;

public class DaoCodeGenerator extends BaseCodeGenerator {

    @Override
    protected String getTemplateFileName() {
        return "dao.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "dao/" + entity.getName() + "Dao.java";
    }


}
