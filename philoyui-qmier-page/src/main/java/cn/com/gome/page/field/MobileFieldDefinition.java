package cn.com.gome.page.field;

public class MobileFieldDefinition extends StringFieldDefinition{

    public MobileFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Mobile;
    }
}
