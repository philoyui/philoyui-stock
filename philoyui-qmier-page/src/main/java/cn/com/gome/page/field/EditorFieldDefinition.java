package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.utils.BeanUtils;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.form.FormField;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class EditorFieldDefinition extends FieldDefinition{

    public EditorFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {

    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        return null;
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {
        return value.toString();
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {
        Object requestValue = "";

        if (entity != null) {
            requestValue = BeanUtils.getPropertyValue(entity, fieldName);
        }

        if(requestValue==null){
            requestValue = "";
        }

        return String.format("<div id=\"%s\">%s</div><textarea style=\"display:none;\"  id=\"textarea_%s\" name=\"%s\"></textarea>",fieldName, requestValue,fieldName,fieldName);
    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        return entity.toString();
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Editor;
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[]{"/resources/lib/wangEditor/wangEditor.min.js","/resources/js/form_editor.js"};
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return parameterValue;
    }
}
