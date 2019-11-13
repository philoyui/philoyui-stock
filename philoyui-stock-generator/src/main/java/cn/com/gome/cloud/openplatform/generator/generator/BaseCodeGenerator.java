package cn.com.gome.cloud.openplatform.generator.generator;

import cn.com.gome.cloud.openplatform.generator.CodeGenerator;
import cn.com.gome.cloud.openplatform.generator.PageProjectInitializer;
import cn.com.gome.cloud.openplatform.generator.domain.FieldConfig;
import cn.com.gome.cloud.openplatform.generator.domain.GenerateContext;
import cn.com.gome.cloud.openplatform.generator.domain.GenerateEntity;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseCodeGenerator implements CodeGenerator {

    @Override
    public void generateCode(GenerateContext context, GenerateEntity entity) {
        String targetFile = context.getBasePath() + "\\" + this.getTargetFilePath(entity);
        String ftlPath = PageProjectInitializer.class.getClassLoader().getResource("ftl").getPath();
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            configuration.setClassForTemplateLoading(this.getClass(), "/ftl");
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate(this.getTemplateFileName());
            Map<String, Object> root = new HashMap<>();
            root.put("basePackage", context.getBasePackage());
            root.put("entityFolder", context.getEntityFolder());
            root.put("EntityName", entity.getName());
            root.put("domainName", entity.getDomainName());
            root.put("EntityChineseName", entity.getChineseName());
            root.put("entityName", StringUtils.uncapitalize(entity.getName()));
            root.put("fieldConfigs", entity.getFieldConfigs());
            root.put("orderFieldConfigs", filterOrder(entity.getFieldConfigs()));
            root.put("filterFieldConfigs", filterFilter(entity.getFieldConfigs()));

            root.put("appName", context.getAppName());
            File file = new File(targetFile);
            if(!file.exists()){
                file.createNewFile();
            }
            Writer out = new FileWriter(file);
            template.process(root, out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private List<FieldConfig> filterOrder(List<FieldConfig> fieldConfigs) {
        ArrayList<FieldConfig> orderFieldConfigs = Lists.newArrayList();
        for (FieldConfig fieldConfig : fieldConfigs) {
            if(fieldConfig.isOrder()){
                orderFieldConfigs.add(fieldConfig);
            }
        }
        return orderFieldConfigs;
    }

    private List<FieldConfig> filterFilter(List<FieldConfig> fieldConfigs) {
        ArrayList<FieldConfig> filterFieldConfigs = Lists.newArrayList();
        for (FieldConfig fieldConfig : fieldConfigs) {
            if(fieldConfig.isFilter()){
                filterFieldConfigs.add(fieldConfig);
            }
        }
        return filterFieldConfigs;
    }

    protected abstract String getTemplateFileName();

    protected abstract String getTargetFilePath(GenerateEntity entity);
}
