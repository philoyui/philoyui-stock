package cn.com.gome.page.plugins.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传插件接口
 */
public interface FileUploadPlugin {

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    String fileUpload(MultipartFile multipartFile);

}
