package cn.com.gome.page.field;


import cn.com.gome.page.form.FormField;

import java.io.Serializable;

public class IntegerFieldDefinition extends StringFieldDefinition {
    public IntegerFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Int;
    }

    @Override
    public Object transferType(Serializable entity, FormField formField, String parameterValue) {
        return Integer.parseInt(parameterValue);
    }
}
