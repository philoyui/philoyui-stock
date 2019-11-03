package cn.com.gome.page.column;

import cn.com.gome.page.utils.BeanUtils;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.field.FieldDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CheckboxTableColumn extends TableColumn{

    public CheckboxTableColumn(Map<String, FieldDefinition> fieldNameDefinitionMap, String fieldName, int width) {
        super(fieldNameDefinitionMap,fieldName, width);
        this.setColumnName("<input type=\"checkbox\" name=\"check-all\" value=\"\">");
    }

    @Override
    public String generateTableValueHtml(HttpServletRequest request, PageConfig pageConfig, TableColumn tableColumn, Object entity) {
        Object id = BeanUtils.getPropertyValue(entity, "id");
        return "<input type=\"checkbox\" value=\"" + id + "\" name=\"id\">";
    }

}
