package io.philoyui.qmier.qmiermanager;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseCodeGenerator implements CodeGenerator {

    @Override
    public void generateCode(GenerateContext context, GenerateEntity entity) {
        String targetFile = context.getBasePath() + "\\" + this.getTargetFilePath(entity);
        String ftlPath = PageProjectInitializer.class.getClassLoader().getResource("ftl").getPath();
        try {
            Configuration configuration = new Configuration(Configuration.getVersion());
            configuration.setDirectoryForTemplateLoading(new File(ftlPath + "/"));
            configuration.setDefaultEncoding("utf-8");
            Template template = configuration.getTemplate(this.getTemplateFileName());
            Map<String, Object> root = new HashMap<>();
            root.put("basePackage", context.getBasePackage());
            root.put("entityFolder", context.getEntityFolder());
            root.put("EntityName", entity.getName());
            root.put("entityName", StringUtils.uncapitalize(entity.getName()));
            root.put("fieldTypes", entity.getFieldConfigs());
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

    protected abstract String getTemplateFileName();

    protected abstract String getTargetFilePath(GenerateEntity entity);
}
