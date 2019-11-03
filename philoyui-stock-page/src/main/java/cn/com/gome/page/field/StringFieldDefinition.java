package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.Restrictions;
import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.utils.BeanUtils;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.form.FormField;
import cn.com.gome.page.plugins.style.StylePlugin;
import com.google.common.base.Strings;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

public class StringFieldDefinition extends FieldDefinition {

    public StringFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {

        String parameterValue = request.getParameter(fieldName);

        if(!Strings.isNullOrEmpty(parameterValue)){
            switch (filterPattern){
                case "like":
                    searchFilter.add(Restrictions.like(fieldName,parameterValue));
                    break;
                default:
                    searchFilter.add(Restrictions.eq(fieldName,parameterValue));
            }
        }

    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {

        String fieldValue = request.getParameter(fieldName);

        StylePlugin stylePlugin = pageContext.getCurrentStyle();

        String listViewFilterTextInputHtml = stylePlugin.getListViewFilterTextInputHtml();

        return String.format(listViewFilterTextInputHtml, fieldName, description, fieldValue == null ? "" : fieldValue);
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {
        return value.toString();
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {

        StylePlugin currentStyle = pageContext.getCurrentStyle();

        Object requestValue = "";

        if (entity != null) {
            requestValue = BeanUtils.getPropertyValue(entity, fieldName);
        }

        return String.format(currentStyle.getFormViewTextInputHtml(), requestValue == null ? "" : requestValue, fieldName, fieldName);

    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        return entity.toString();
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.String;
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[0];
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return parameterValue;
    }
}
