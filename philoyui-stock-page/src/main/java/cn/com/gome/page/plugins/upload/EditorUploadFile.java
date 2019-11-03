package cn.com.gome.page.plugins.upload;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yangyu-ds on 2018/5/4.
 */
public class EditorUploadFile  implements Serializable {

    private int errno;

    private List<String> data;

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public static EditorUploadFile createSuccessResponse(String imagePath) {
        EditorUploadFile editorUploadFile = new EditorUploadFile();
        editorUploadFile.setErrno(0);
        editorUploadFile.setData(Lists.newArrayList(imagePath));
        return editorUploadFile;
    }

    public static EditorUploadFile createFailResponse(String errorMsg) {
        EditorUploadFile editorUploadFile = new EditorUploadFile();
        editorUploadFile.setErrno(1);
        editorUploadFile.setData(Lists.newArrayList(""));
        return editorUploadFile;
    }
}
