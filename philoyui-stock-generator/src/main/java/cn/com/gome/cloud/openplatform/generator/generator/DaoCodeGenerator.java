package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

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
