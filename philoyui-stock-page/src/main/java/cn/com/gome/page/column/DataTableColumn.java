package cn.com.gome.page.column;

import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.excp.PageException;
import cn.com.gome.page.field.FieldDefinition;
import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class DataTableColumn extends TableColumn{

    public DataTableColumn(Map<String, FieldDefinition> fieldNameDefinitionMap, String fieldName, int width) {

        super(fieldNameDefinitionMap,fieldName, width);

        FieldDefinition fieldDefinition = fieldNameDefinitionMap.get(fieldName);

        if(fieldDefinition==null){
            throw new PageException("表格列tableColumn设置中，字段不存在：" + fieldName);
        }

        this.setColumnName(fieldDefinition.getDescription());
        this.setFieldDefinition(fieldDefinition);
    }

    @Override
    public String generateTableValueHtml(HttpServletRequest request, PageConfig pageConfig, TableColumn tableColumn, Object entity) {


        Object value;
        try {
            value = PropertyUtils.getProperty(entity, this.getFieldName());
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new PageException("格式化表格数值出错，fieldName为" + this.getFieldName(), e);
        }

        if(value==null){
            return "";
        }

        return fieldDefinition.formatColumnValue(pageConfig, value);

    }

}
