package cn.com.gome.page.field;

public class DisplayStringFieldDefinition extends StringFieldDefinition{

    public DisplayStringFieldDefinition(String fieldName, String description) {
        super(fieldName, description);
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.Display;
    }
}
