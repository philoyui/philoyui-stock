package io.philoyui.qmier.qmiermanager.config;

import cn.com.gome.page.plugins.upload.FileUploadPlugin;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AliYunUploadPlugin implements FileUploadPlugin {

    public static final Logger logger = LoggerFactory.getLogger(AliYunUploadPlugin.class);

    // endpoint
    private String endpoint = "oss-cn-beijing.aliyuncs.com";

    // accessKey
    private String accessKeyId = "LTAI4Fx36HdmB3g4KyR8dWxJ";

    private String accessKeySecret = "HobPTtHrgCgx0E1BlhQvIJYxWIGIWs";

    // 空间
    private String bucketName = "yang7551735";

    // 文件存储目录
    private String fileDir = "credit";

    private OSSClient ossClient;

    @Override
    public String fileUpload(MultipartFile multipartFile) {

        //初始化OSSClient
        try {
            String path = multipartFile.getOriginalFilename();
            String fileName = path.substring(path.lastIndexOf("\\")+1);
            String type = path.substring(path.lastIndexOf(".")+1);
            int random = (int)(Math.random()*900)+10000;
            String newFileName = fileName.replace(fileName, String.valueOf(System.currentTimeMillis())+String.valueOf(random)+"."+type);
            String filePath = fileDir + "/" + newFileName;
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(multipartFile.getSize());
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentEncoding("utf-8");
            metadata.setContentType(getContentType(newFileName));
            metadata.setContentDisposition("filename/filesize=" + newFileName + "/" + multipartFile.getSize() + "Byte.");
            getOssClient().putObject(bucketName, filePath, multipartFile.getInputStream(), metadata);
            getOssClient().setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            return "https://" + bucketName + "." + endpoint + "/" + filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private OSS getOssClient() {
        return ossClient;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName 文件名
     * @return 文件的contentType
     */
    private static String getContentType(String fileName){
        //文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if(".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if(".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if(".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)  || ".png".equalsIgnoreCase(fileExtension) ) {
            return "image/jpeg";
        }
        if(".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if(".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if(".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if(".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if(".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if(".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        //默认返回类型
        return "image/jpeg";
    }


}
