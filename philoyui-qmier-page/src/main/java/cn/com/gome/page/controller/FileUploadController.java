package cn.com.gome.page.controller;

import cn.com.gome.page.core.PageManager;
import cn.com.gome.page.plugins.upload.EditorUploadFile;
import cn.com.gome.page.plugins.upload.FileUploadPlugin;
import cn.com.gome.page.plugins.upload.FormUploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @Autowired
    private PageManager pageManager;

    /**
     * 表单的图片上传
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/formImageUpload")
    public FormUploadFile formImageUpload(@RequestParam("multipartFile") MultipartFile multipartFile){

        FileUploadPlugin fileUploadPlugin = pageManager.getFileUploadPlugin();

        try{
            return FormUploadFile.successFile(fileUploadPlugin.fileUpload(multipartFile));
        } catch (Exception ex) {
            return FormUploadFile.failFile(ex.getMessage());
        }
    }

    @PostMapping("/uploadImg")
    public EditorUploadFile uploadImg(@RequestParam("filedata") MultipartFile multipartFile){
        FileUploadPlugin fileUploadPlugin = pageManager.getFileUploadPlugin();
        try{
            return EditorUploadFile.createSuccessResponse(fileUploadPlugin.fileUpload(multipartFile));
        } catch (Exception ex) {
            return EditorUploadFile.createFailResponse("错误: " + ex.getMessage());
        }
    }

}
