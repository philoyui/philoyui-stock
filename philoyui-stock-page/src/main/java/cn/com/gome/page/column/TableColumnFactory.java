package cn.com.gome.page.column;

import cn.com.gome.page.excp.GmosException;
import cn.com.gome.page.field.FieldDefinition;

import java.util.Map;

public class TableColumnFactory {

    public TableColumn select(Map<String, FieldDefinition> fieldNameDefinitionMap, String columnDefinition) {

        String[] columnParts = columnDefinition.split("_");

        if(columnParts.length!=2){
            throw new GmosException("表格列tableColumn设置出错，格式出错，有且必须有一个_：" + columnDefinition);
        }

        String fieldDefinition = columnParts[0];
        int width = Integer.parseInt(columnParts[1]);

        switch (fieldDefinition){
            case "#checkbox":
                return new CheckboxTableColumn(fieldNameDefinitionMap, fieldDefinition, width);
            case "#operation":
                return new OperationTableColumn(fieldNameDefinitionMap, fieldDefinition, width);
            default:
                return new DataTableColumn(fieldNameDefinitionMap, fieldDefinition, width);
        }

    }

}
