package cn.com.gome.page.column;

import cn.com.gome.page.button.column.ColumnAction;
import cn.com.gome.page.core.PageConfig;
import cn.com.gome.page.field.FieldDefinition;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class OperationTableColumn extends TableColumn{

    public OperationTableColumn(Map<String, FieldDefinition> fieldNameDefinitionMap, String columnName, int width) {
        super(fieldNameDefinitionMap,columnName, width);
        this.setColumnName("操作");
    }

    @Override
    public String generateTableValueHtml(HttpServletRequest request, PageConfig pageConfig, TableColumn tableColumn, Object entity) {

        ColumnAction[] columnActions = pageConfig.getColumnActions();

        StringBuilder stringBuilder = new StringBuilder();


        for (ColumnAction columnAction : columnActions) {

            stringBuilder.append(columnAction.displayButtons(request,pageConfig, entity));

        }

        return stringBuilder.toString();
    }

}
