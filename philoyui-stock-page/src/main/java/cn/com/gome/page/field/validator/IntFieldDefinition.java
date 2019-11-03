package cn.com.gome.page.field.validator;


import cn.com.gome.page.field.FieldType;
import cn.com.gome.page.field.StringFieldDefinition;
import cn.com.gome.page.form.FormField;

import java.io.Serializable;

public class IntFieldDefinition extends StringFieldDefinition {
    public IntFieldDefinition(String fieldName, String description) {
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
