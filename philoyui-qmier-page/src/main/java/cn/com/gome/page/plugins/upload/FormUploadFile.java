package cn.com.gome.page.plugins.upload;

import java.io.Serializable;

/**
 *
 * 表单文件上传
 *
 * Created by yangyu-ds on 2018/5/4.
 */
public class FormUploadFile implements Serializable{

    private boolean success = true;

    private String file;

    private String message;

    public FormUploadFile() {
    }

    public FormUploadFile(String fileName) {
        this.file = fileName;
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static FormUploadFile successFile(String fileName) {
        return new FormUploadFile(fileName);
    }

    public static FormUploadFile failFile(String message) {
        FormUploadFile formUploadFile = new FormUploadFile();
        formUploadFile.setSuccess(false);
        formUploadFile.setMessage(message);
        return formUploadFile;
    }
}
