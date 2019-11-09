package io.philoyui.qmier.qmiermanager;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageCodeGenerator {


    /**
     * 生成代码
     * 1. 生成dao,service,service.impl,page,controller,event,eventhandler文件夹
     * @param request
     */
    public void generateCode(PageCodeRequest request) {
        GenerateContext generateContext = GenerateContext.construct(request);
        createNeededFolder(generateContext);

        generateDaoCode(generateContext);
        generateServiceInterface(request);
        generateServiceImpl(request);
        generatePageService(request);
        generateController(request);
        generateEvent(request);
        generateEventHandler(request);
    }

    private void generateEventHandler(PageCodeRequest request) {

    }

    private void generateEvent(PageCodeRequest request) {

    }

    private void generateController(PageCodeRequest request) {

    }

    private void generatePageService(PageCodeRequest request) {

    }

    private void generateServiceImpl(PageCodeRequest request) {

    }

    private void generateServiceInterface(PageCodeRequest request) {
    }

    private void generateDaoCode(GenerateContext generateContext) {
        List<GenerateEntity> entities = generateContext.getEntities();
        for (GenerateEntity entity : entities) {
            try {
                Configuration configuration = new Configuration(Configuration.getVersion());
                configuration.setDirectoryForTemplateLoading(new File("ftl directory absolute path"));
                configuration.setDefaultEncoding("utf-8");
                Template template = configuration.getTemplate("dao.ftl");
                Map<String,Object> root = new HashMap<>();
                root.put("basePath", generateContext.getBasePath());
                root.put("entityFolder", generateContext.getEntityFolder());
                root.put("EntityName", generateContext.getEntityName());
                root.put("entityName", StringUtils.uncapitalize(generateContext.getEntityName()));
                root.put("fieldTypes", generateContext.getFieldTypes());
                Writer out = null;
                out = new FileWriter(new File(generateContext.getBasePath() + "/" + generateContext.getEntityName() + "Dao" + ".java"));
                template.process(root, out);
                out.flush();
                out.close();
            } catch (IOException | TemplateException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * 根据
     * @param generateContext
     */
    private void createNeededFolder(GenerateContext generateContext) {
        List<String> folders = generateContext.getNeededFolders();
        for (String folder : folders) {
            File manager = new File(generateContext.getBasePath()+"\\" + folder);
            try {
                FileUtils.forceMkdir(manager);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
