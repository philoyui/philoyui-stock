package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.utils.BeanUtils;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.form.FormField;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class ImageFieldDefinition extends FieldDefinition{

    private final int width;

    private final int height;

    public ImageFieldDefinition(String fieldName, String description, int width, int height) {
        super(fieldName, description);
        this.width = width;
        this.height = height;
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {

    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        return null;
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object entity) {
        String defaultImagePath = "http://icons.iconarchive.com/icons/blackvariant/shadow135-system/1024/TextEdit-icon.png";
        String unit = "px";

        // 判断是否使用默认图片地址
        String resultImagePath;
        // 避免出现(String) null的情况
        if (entity == null) {
            resultImagePath = defaultImagePath;
        } else {
            resultImagePath = Strings.isNullOrEmpty((String) entity) ? defaultImagePath : (String) entity;
        }

        return String.format("<a href=\"%s\" target=\"_blank\"><image width=\"%d%s\" height=\"%d%s\" src=\"%s\" /></a>",
                resultImagePath,
                width, unit,
                height, unit,
                resultImagePath);
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {

        String imagePath = (String) BeanUtils.getPropertyValue(entity, fieldName);

        String imageContent = "";

        if(!Strings.isNullOrEmpty(imagePath)){
            imageContent = "<a target=\"_blank\" href=\"" + imagePath + "\"><img width=\"200\" src=\"" + imagePath + "\"></a>";
        }

        String inputFormContent = String.format("<input type=\"hidden\" class=\"input-text\" value=\"%s\" placeholder=\"\" id=\"%s\" name=\"%s\">", imagePath, fieldName, fieldName);
        return "<div class=\"uploader-thum-container\">" + inputFormContent + "<div id=\"picBox_" + fieldName + "\" class=\"uploader-list\">" + imageContent + "</div><div id=\"filePicker_" + fieldName + "\"><div id=\"upload-btn_" + fieldName + "\" class=\"webuploader-pick\">选择图片</div><div id=\"errormsg_" + fieldName + "\"></div><div id=\"pic-progress-wrap_" + fieldName + "\"></div></div></div>";
    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        String imageContent = "";

        if(!Strings.isNullOrEmpty(fieldName)){
            imageContent = "<a target=\"_blank\" href=\"" + fieldName + "\"><img width=\"200\" src=\"" + fieldName + "\"></a>";
        }

        String inputFormContent = String.format("<input type=\"hidden\" class=\"input-text\" value=\"%s\" placeholder=\"\" id=\"%s\" name=\"%s\">", fieldName, fieldName, fieldName);
        return "<div class=\"uploader-thum-container\">" + inputFormContent + "<div id=\"picBox_" + fieldName + "\" class=\"uploader-list\">" + imageContent + "</div><div id=\"filePicker_" + fieldName + "\"><div id=\"upload-btn_" + fieldName + "\" class=\"webuploader-pick\">选择图片</div><div id=\"errormsg_" + fieldName + "\"></div><div id=\"pic-progress-wrap_" + fieldName + "\"></div></div></div>";
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Image;
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[]{"/resources/js/SimpleAjaxUploader.min.js","/resources/js/form_image.js"};
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return parameterValue;
    }
}
