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

        switch (columnParts[0]){
            case "#checkbox":
                return new CheckboxTableColumn(fieldNameDefinitionMap,columnParts[0],Integer.parseInt(columnParts[1]));
            case "#operation":
                return new OperationTableColumn(fieldNameDefinitionMap,columnParts[0],Integer.parseInt(columnParts[1]));
            default:
                return new DataTableColumn(fieldNameDefinitionMap,columnParts[0],Integer.parseInt(columnParts[1]));
        }

    }

}
