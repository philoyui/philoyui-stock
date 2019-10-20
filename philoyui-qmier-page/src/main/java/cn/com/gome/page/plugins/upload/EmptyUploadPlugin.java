package cn.com.gome.page.plugins.upload;

import cn.com.gome.page.excp.PageException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class EmptyUploadPlugin implements FileUploadPlugin{

    @Override
    public String fileUpload(MultipartFile multipartFile) {
        throw new PageException("不支持文件上传");
    }
}
