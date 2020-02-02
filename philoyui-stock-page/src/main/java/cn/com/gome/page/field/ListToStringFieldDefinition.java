package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.excp.GmosException;
import cn.com.gome.page.form.FormField;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;
import java.util.function.Function;

public class ListToStringFieldDefinition extends FieldDefinition{

    /**
     * 展示前预处理
     */
    protected Function<Object, List<String>> stringToListFunction;

    public ListToStringFieldDefinition(String fieldName, String description,Function<Object, List<String>> stringToListFunction) {
        super(fieldName, description);
        this.stringToListFunction = stringToListFunction;
    }

    @Override
    public void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern) {
        throw new GmosException("此字段不支持复杂查询");
    }

    @Override
    public String buildSearchFilterHtml(PageService pageService, HttpServletRequest request) {
        throw new GmosException("此字段不支持复杂查询");
    }

    @Override
    public String formatColumnValue(PageConfig pageConfig, Object value) {
        List<String> tagNameList = stringToListFunction.apply(value);
        return StringUtils.join(tagNameList,",");
    }

    @Override
    public String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity) {
        throw new GmosException("此字段不支持复杂查询");
    }

    @Override
    public String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity) {
        List<String> tagNameList = stringToListFunction.apply(entity);
        return StringUtils.join(tagNameList,",");
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.ListToString;
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
