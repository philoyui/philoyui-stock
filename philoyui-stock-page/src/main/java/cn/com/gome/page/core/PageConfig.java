package cn.com.gome.page.core;


import cn.com.gome.page.button.batch.TableAction;
import cn.com.gome.page.button.column.ColumnAction;
import cn.com.gome.page.column.TableColumn;
import cn.com.gome.page.column.TableColumnFactory;
import cn.com.gome.page.field.*;
import cn.com.gome.page.form.FormField;
import cn.com.gome.page.plugins.style.StylePlugin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageConfig {

    /**
     * 级联菜单的字段列表
     */
    private List<String> linkInFieldIds = new ArrayList<>();

    /**
     * 级联菜单域名列表
     */
    private List<String> linkInFieldDomains = new ArrayList<>();

    /**
     * 领域对象名称
     */
    private String domainName;

    /**
     * 领域对象中文名称
     */
    private String domainChineseName;


    /**
     * 领域对象类
     */
    private Class<? extends Serializable> domainClass;

    /**
     * 当前登录用户绑定的字段，由于后台只能显示该登录用户的信息
     */
    private String loginUserFieldName;

    /**
     * 排序方式的定义
     */
    private SortDefinition sortDefinitions;

    /**
     * 列表页筛选条件
     */
    private List<FilterField> filterFields = new ArrayList<>();

    /**
     * 表单项定义
     */
    private List<FormField> formFields = new ArrayList<>();

    /**
     * 表格按钮操作
     */
    private ColumnAction[] columnActions = new ColumnAction[]{};

    /**
     * 表格操作
     */
    private TableAction[] tableActions = new TableAction[]{};


    private List<TableColumn> tableColumns = new ArrayList<>();
    /**
     * 字段名称->字段定义
     */
    private Map<String, FieldDefinition> fieldNameDefinitionMap = new HashMap<>();

    /**
     * Page的全局上下文
     */
    private PageContext pageContext;

    public PageConfig(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public PageConfig withDomainName(String domainName) {
        this.domainName = domainName;
        return this;
    }

    public PageConfig withDomainChineseName(String domainChineseName) {
        this.domainChineseName = domainChineseName;
        return this;
    }

    public PageConfig withDomainClass(Class<? extends Serializable> domainClass) {
        this.domainClass = domainClass;
        return this;
    }

    /**
     * 字段定义 转化为 fieldName -> FieldDefinition
     * @param fieldDefinitions
     * @return
     */
    public PageConfig withFieldDefinitions(FieldDefinition... fieldDefinitions) {
        for (FieldDefinition fieldDefinition : fieldDefinitions) {
            fieldDefinition.setPageContext(pageContext);
            fieldNameDefinitionMap.put(fieldDefinition.getFieldName(),fieldDefinition);

            if(fieldDefinition.getFieldType() == FieldType.LinkIn){
                LinkInFieldDefinition linkInFieldDefinition = (LinkInFieldDefinition) fieldDefinition;
                this.linkInFieldIds.add(linkInFieldDefinition.getFieldName());
                this.linkInFieldDomains.add(linkInFieldDefinition.getLinkInProvider().getDomainName());
            }

        }
        return this;
    }

    public PageConfig withLoginUserFieldName(String loginUserFieldName) {
        this.loginUserFieldName = loginUserFieldName;
        return this;
    }

    public PageConfig withSortDefinitions(String... sortDefinitions) {
        this.sortDefinitions = SortDefinition.parse(this,fieldNameDefinitionMap,sortDefinitions);
        return this;
    }

    public PageConfig withFilterDefinitions(String... filterDefinitions) {
        for (String filterDefinition : filterDefinitions) {
            filterFields.add(FilterField.resolveFilterField(fieldNameDefinitionMap,filterDefinition));
        }
        return this;
    }


    public PageConfig withTableColumnDefinitions(String... columnDefinitions) {

        TableColumnFactory tableColumnFactory = pageContext.getTableColumnFactory();
        for (String columnDefinition : columnDefinitions) {
            TableColumn tableColumn = tableColumnFactory.select(fieldNameDefinitionMap,columnDefinition);
            tableColumns.add(tableColumn);
        }

        return this;
    }


    public PageConfig withColumnAction(ColumnAction... columnActions) {
        this.columnActions = columnActions;
        return this;
    }

    public PageConfig withFormItemDefinition(String... formItemDefinitions) {
        for (String formItemDefinition : formItemDefinitions) {
            formFields.add(FormField.resolve(this,fieldNameDefinitionMap,formItemDefinition));
        }
        return this;
    }

    public PageConfig withTableAction(TableAction... tableAction) {
        this.tableActions = tableAction;
        return this;
    }

    public List<FilterField> getFilterFields() {
        return filterFields;
    }

    public String getLoginUserFieldName() {
        return loginUserFieldName;
    }

    public SortDefinition getSortDefinition() {
        return sortDefinitions;
    }

    public String getDomainChineseName() {
        return domainChineseName;
    }

    public String getDomainName() {
        return domainName;
    }

    public boolean existFilter() {
        return filterFields.size()!=0;
    }

    public TableAction[] getTableActions() {
        return tableActions;
    }

    public List<TableColumn> getTableColumns() {
        return tableColumns;
    }

    public ColumnAction[] getColumnActions() {
        return columnActions;
    }

    public List<FormField> getFormFields() {
        return formFields;
    }

    public Class<? extends Serializable> getDomainClass() {
        return domainClass;
    }

    public PageContext getPageContext() {
        return pageContext;
    }

    public StylePlugin getStylePlugin() {
        return pageContext.getStylePlugin();
    }

    public Map<String, FieldDefinition> getFieldNameDefinitionMap() {
        return fieldNameDefinitionMap;
    }

    public List<String> getLinkInFieldIds() {
        return linkInFieldIds;
    }

    public List<String> getLinkInFieldDomains() {
        return linkInFieldDomains;
    }

}

