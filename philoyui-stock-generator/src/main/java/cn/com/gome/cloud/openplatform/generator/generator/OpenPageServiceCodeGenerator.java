package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public class OpenPageServiceCodeGenerator extends BaseCodeGenerator {
    @Override
    protected String getTemplateFileName() {
        return "open_page_service.ftl";
    }

    @Override
    protected String getTargetFilePath(GenerateEntity entity) {
        return "page/"+ entity.getName() + "PageService.java";
    }
}
