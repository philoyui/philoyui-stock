package cn.com.gome.page.field;

import cn.com.gome.cloud.openplatform.common.SearchFilter;
import cn.com.gome.page.core.PageService;
import cn.com.gome.page.field.validator.FieldValidator;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.core.PageContext;
import cn.com.gome.page.form.FormField;
import com.google.common.collect.Lists;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 页面对象是字段定义
 */
public abstract class FieldDefinition {

    /**
     * 字段名
     */
    protected String fieldName;

    /**
     * 字段描述
     */
    protected String description;

    /**
     * 是否在表单页必须
     */
    protected boolean require = false;

    /**
     * 全局页面的配置
     */
    protected PageContext pageContext;

    /**
     * 最大长度
     */
    protected int maxSize;

    /**
     * 最小长度
     */
    protected int minSize;


    /**
     * 字段校验器
     */
    private List<FieldValidator> fieldValidators = Lists.newArrayList();


    public FieldDefinition(String fieldName, String description) {
        this.fieldName = fieldName;
        this.description = description;
    }


    public FieldDefinition required(){
        this.require = true;
        return this;
    }

    public abstract void constructSearchFilter(HttpServletRequest request, SearchFilter searchFilter, String filterPattern);

    public abstract String buildSearchFilterHtml(PageService pageService, HttpServletRequest request);

    public String getFieldName() {
        return fieldName;
    }

    public String getDescription() {
        return description;
    }

    public boolean isRequire() {
        return require;
    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public abstract String formatColumnValue(PageConfig pageConfig, Object value);

    public abstract String generateFormItemHtml(PageService pageService, HttpServletRequest request, Object entity);

    public abstract String generateFormItemReadHtml(PageConfig pageConfig, HttpServletRequest request, Object entity);

    public FieldDefinition withMaxSize(int maxSize){
        this.maxSize = maxSize;
        return this;
    }

    public FieldDefinition withMinSize(int minSize){
        this.minSize = minSize;
        return this;
    }


    public boolean isRequired() {
        return require;
    }

    public abstract FieldType getFieldType();

    public abstract String[] getNeedJsFiles();

    public List<FieldValidator> getFieldValidators() {
        return fieldValidators;
    }

    public abstract Object transferType(Serializable entity, FormField formField, String parameterValue);
}
