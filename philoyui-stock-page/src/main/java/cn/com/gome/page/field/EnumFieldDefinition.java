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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EnumFieldDefinition extends FieldDefinition {

    private Class<? extends Enum<?>> enumClass;

    private Map<String,Enum<?>> nameEnumMap = new ConcurrentHashMap<>();

    private Map<String,String> nameDescMap = new ConcurrentHashMap<>();

    public EnumFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    public EnumFieldDefinition(String fieldName, String description, Class<? extends Enum<?>> enumClass) {
        this(fieldName, description);
        this.enumClass = enumClass;
        for (Enum enumConstant : enumClass.getEnumConstants()) {
            nameEnumMap.put(enumConstant.name(), enumConstant);
            nameDescMap.put(enumConstant.name(),enumConstant.toString());
        }

    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {
        String fieldValue = request.getParameter(fieldName);
        if (!Strings.isNullOrEmpty(fieldValue)) {
            searchFilter.add(Restrictions.eq(fieldName, nameEnumMap.get(fieldValue)));
        }
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        StylePlugin stylePlugin = pageContext.getStylePlugin();
        String selectValue = request.getParameter(fieldName);
        return stylePlugin.buildSelectBoxFilterHtml(nameDescMap,fieldName,description,selectValue);
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {
        return value.toString();
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {

        String selectValue = "";

        if (entity != null) {
            Enum<?> requestValue = (Enum<?>) BeanUtils.getPropertyValue(entity, fieldName);
            if(requestValue!=null){
                selectValue = requestValue.name();
            }
        }

        return  pageContext.getStylePlugin().buildSelectBoxFilterHtml(nameDescMap,fieldName,description,selectValue);

    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        return entity.toString();
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Enum;
    }

    @Override
    public String[] getNeedJsFiles() {
        return new String[0];
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return nameEnumMap.get(parameterValue);
    }

}
