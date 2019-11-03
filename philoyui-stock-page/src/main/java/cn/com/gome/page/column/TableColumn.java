package cn.com.gome.page.column;


import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.field.FieldDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class TableColumn {

    protected String fieldName;

    /**
     * 列名，默认为FieldDefinition中的FieldName,
     */
    protected String columnName;


    protected int width;

    protected Map<String, FieldDefinition> fieldNameDefinitionMap;

    protected FieldDefinition fieldDefinition;


    public TableColumn(Map<String, FieldDefinition> fieldNameDefinitionMap,String fieldName, int width) {
        this.fieldName = fieldName;
        this.width = width;
        this.fieldNameDefinitionMap = fieldNameDefinitionMap;
    }

    public String getColumnName() {
        return columnName;
    }

    public int getWidth() {
        return width;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public abstract String generateTableValueHtml(HttpServletRequest request, PageConfig pageConfig, TableColumn tableColumn, Object entity);

    public FieldDefinition getFieldDefinition() {
        return fieldDefinition;
    }

    public void setFieldDefinition(FieldDefinition fieldDefinition) {
        this.fieldDefinition = fieldDefinition;
    }
}
