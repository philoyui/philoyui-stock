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
import java.util.HashMap;
import java.util.Map;

/**
 * 是否开启字段定义
 */
public class EnableFieldDefinition extends FieldDefinition{

    public static final String ENABLE_TEXT = "已启用";

    public static final String DISABLE_TEXT = "已停用";

    public EnableFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {
        String fieldValue = request.getParameter(fieldName);
        if (!Strings.isNullOrEmpty(fieldValue)) {
            searchFilter.add(Restrictions.eq(fieldName, Boolean.valueOf(fieldValue)));
        }
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {

        Map<String, String> optionMap = getEnableStringMap();

        StylePlugin currentStyle = pageContext.getCurrentStyle();

        String fieldValue = request.getParameter(fieldName);

        return currentStyle.buildSelectBoxFilterHtml(optionMap,fieldName,description,fieldValue == null ? "" : fieldValue);

    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object entity) {
        Boolean boolValue = (Boolean) entity;
        StylePlugin currentStyle = pageContext.getCurrentStyle();
        return currentStyle.buildEnableHtml(boolValue);
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {

        Map<String, String> optionMap = getEnableStringMap();

        StylePlugin currentStyle = pageContext.getCurrentStyle();

        Object requestValue = "";

        if (entity != null) {
            requestValue = BeanUtils.getPropertyValue(entity, fieldName);
        }

        return currentStyle.buildSelectBoxFilterHtml(optionMap,fieldName,description,requestValue.toString());
    }

    private Map<String, String> getEnableStringMap() {
        Map<String, String> optionMap = new HashMap<>();
        optionMap.put("true", ENABLE_TEXT);
        optionMap.put("false", DISABLE_TEXT);
        return optionMap;
    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        Boolean booleanValue = (Boolean) entity;
        if (booleanValue) {
            return ENABLE_TEXT;
        } else {
            return DISABLE_TEXT;
        }
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Enable;
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[0];
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return Boolean.parseBoolean(parameterValue);
    }
}
