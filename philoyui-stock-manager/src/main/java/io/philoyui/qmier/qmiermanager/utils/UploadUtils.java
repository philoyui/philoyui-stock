package io.philoyui.qmier.qmiermanager.utils;

import java.io.File;

public class UploadUtils {

    public final static String IMG_PATH_PREFIX = "static/upload/voice/";

    public static File getImgDirFile(){
        String fileDirPath = new String("philoyui-stock-manager/src/main/resources/" + IMG_PATH_PREFIX);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        return fileDir;
    }

}
