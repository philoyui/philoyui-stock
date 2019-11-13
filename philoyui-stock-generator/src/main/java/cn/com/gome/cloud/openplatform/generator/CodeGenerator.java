package cn.com.gome.cloud.openplatform.generator;

import cn.com.gome.cloud.openplatform.generator.domain.GenerateContext;
import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;

public interface CodeGenerator {
    void generateCode(GenerateContext context, GenerateEntity entity);
}
