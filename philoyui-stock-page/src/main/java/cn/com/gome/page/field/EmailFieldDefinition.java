package cn.com.gome.page.field;

public class EmailFieldDefinition  extends StringFieldDefinition{

    public EmailFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Email;
    }
}
