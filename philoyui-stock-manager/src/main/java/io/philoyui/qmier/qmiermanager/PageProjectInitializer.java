package io.philoyui.qmier.qmiermanager;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class PageProjectInitializer {

    /**
     * 生成代码
     * 1. 生成dao,service,service.impl,page,controller,event,eventhandler文件夹
     * @param request
     */
    public void generateCode(PageCodeRequest request) {
        GenerateContext generateContext = GenerateContext.construct(request);
        createNeededFolder(generateContext);
        for (GenerateEntity entity : generateContext.getEntities()) {
            List<CodeGenerator> codeGenerators = generateContext.getCodeGenerators();
            for (CodeGenerator codeGenerator : codeGenerators) {
                codeGenerator.generateCode(generateContext, entity);
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
